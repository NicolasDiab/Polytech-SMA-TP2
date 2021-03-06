/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
 *****************************************************************/

package sma.travelBooking;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.UnreadableException;

import java.time.ZonedDateTime;

public class TravelBuyerAgent extends Agent {
	// The ticket's informations to buy
	private String targetDestination;
	private int maxPrice;
	private int nbTries;

	private ZonedDateTime dateTimeDeparture;

	// The list of known seller agents
	private AID[] sellerAgents;

	// Put agent initializations here
	protected void setup() {
		// Printout a welcome message
		System.out.println("Hallo! Buyer-agent "+getAID().getName()+" is ready.");
		nbTries = 0;

		// Get the destination of the flight to buy as a start-up argument
		Object[] args = getArguments();
		if (args != null && args.length > 2) {
			targetDestination = (String) args[0];
			System.out.println("Target destination is " + targetDestination);

			maxPrice = Integer.parseInt((String) args[1]);
			System.out.println("Max price is " + maxPrice);

			dateTimeDeparture = ZonedDateTime.parse((String) args[2]); // cast a string to a date
			System.out.println("Agent wants to depart at " + dateTimeDeparture);

			// Add a TickerBehaviour that schedules a request to seller agents every minute
			addBehaviour(new TickerBehaviour(this, 60000) {
				protected void onTick() {
					System.out.println("Trying to buy " + targetDestination);
					// Update the list of seller agents
					DFAgentDescription template = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType("flights-selling");
					template.addServices(sd);
					try {
						DFAgentDescription[] result = DFService.search(myAgent, template); 
						System.out.println("Found the following seller agents:");
						sellerAgents = new AID[result.length];
						for (int i = 0; i < result.length; ++i) {
							sellerAgents[i] = result[i].getName();
							System.out.println(sellerAgents[i].getName());
						}
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}

					// Perform the request
					myAgent.addBehaviour(new RequestPerformer());
				}
			} );
		}
		else {
			// Make the agent terminate
			System.out.println("No target flight destination or max price specified");
			doDelete();
		}
	}

	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Buyer-agent "+getAID().getName()+" terminating.");
	}

	/**
	   Inner class RequestPerformer.
	   This is the behaviour used by travel-buyer agents to request seller
	   agents the target destination.
	 */
	private class RequestPerformer extends Behaviour {
		private AID bestSeller; // The agent who provides the best offer 
		private PlaneTicket cheapestTicket;  // The best offered price ticket
		private int repliesCnt = 0; // The counter of replies from seller agents
		private MessageTemplate mt; // The template to receive replies
		private int step = 0;

		public void action() {
			switch (step) {
			case 0:
				findSellers();
				break;
			case 1:
				receiveProposals();
				break;
			case 2:
				if (cheapestTicket.getPrice() < maxPrice) {
					priceCorrect();
				} else {
					priceTooHigh();
				}
				break;
			case 3:
				receivePurchaseOrderReply();
				break;
			}        
		}

		private void priceTooHigh() {
			ACLMessage order = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
			order.addReceiver(bestSeller);
			order.setContent(targetDestination);
			order.setConversationId("flight-trade");
			order.setReplyWith("order"+System.currentTimeMillis());
			myAgent.send(order);
			// Go back to find sellers and improve price
			if (nbTries < 10) {
				maxPrice *= 1.1;
				nbTries++;
				step = 0; // try again
			} else {
				// No ticket found - stop buying - go to step Done
				bestSeller = null;
				step = 2;
			}
		}

		private void priceCorrect() {
			// Send the purchase order to the seller that provided the best offer
			ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
			order.addReceiver(bestSeller);
			order.setContent(targetDestination);
			order.setConversationId("flight-trade");
			order.setReplyWith("order"+System.currentTimeMillis());
			myAgent.send(order);
			// Prepare the template to get the purchase order reply
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId("flight-trade"),
					MessageTemplate.MatchInReplyTo(order.getReplyWith()));
			step = 3;
		}

		private void receivePurchaseOrderReply() {
			ACLMessage reply;// Receive the purchase order reply
			reply = myAgent.receive(mt);
			if (reply != null) {
                // Purchase order reply received
                if (reply.getPerformative() == ACLMessage.INFORM) {
                    // Purchase successful. We can terminate
                    System.out.println(targetDestination +" successfully purchased flight from agent "+reply.getSender().getName());
                    System.out.println("Price = "+ cheapestTicket.getPrice());
                    myAgent.doDelete();
                }
                else {
                    System.out.println("Attempt failed: requested flight already sold.");
                }

                step = 4;
            }
            else {
                block();
            }
		}

		private void receiveProposals() {
			// Receive all proposals/refusals from seller agents
			ACLMessage reply = myAgent.receive(mt);
			if (reply != null) {
                // Reply received
                if (reply.getPerformative() == ACLMessage.PROPOSE) {
                    // This is an offer
					PlaneTicket planeTicket = null;
					try {
						planeTicket = (PlaneTicket) reply.getContentObject();
					} catch (UnreadableException e) {
						e.printStackTrace();
					}

					// check the ticket
					if (planeTicket != null) {
						// check the date
						if (dateTimeDeparture.getDayOfYear() == planeTicket.getDateTimeDeparture().getDayOfYear()) {
							// then the price
							if (bestSeller == null || planeTicket.getPrice() < cheapestTicket.getPrice()) {
								// This is the cheapest offer at present
								cheapestTicket = planeTicket;
								bestSeller = reply.getSender();
							}
						}
					}
                }
                repliesCnt++;
                if (repliesCnt >= sellerAgents.length) {
                    // We received all replies
                    step = 2;
                }
            }
            else {
                block();
            }
		}

		private void findSellers() {
			// Send the cfp to all sellers
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (int i = 0; i < sellerAgents.length; ++i) {
                cfp.addReceiver(sellerAgents[i]);
            }
			cfp.setContent(targetDestination);
			cfp.setConversationId("flight-trade");
			cfp.setReplyWith("cfp"+System.currentTimeMillis()); // Unique value
			myAgent.send(cfp);
			// Prepare the template to get proposals
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId("flight-trade"),
                    MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			step = 1;
		}

		public boolean done() {
			if (step == 2 && bestSeller == null) {
				System.out.println("Attempt failed: "+ targetDestination +" flights not available for sale");
			}
			return ((step == 2 && bestSeller == null) || step == 4);
		}
	}  // End of inner class RequestPerformer
}
