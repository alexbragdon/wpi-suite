/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
//import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer handles responses to requests for all
 * post board messages.
 * 
 * @author Chris Casola
 *
 * @version $Revision: 1.0 $
 */
public class GetPlanningPokerSessionRequestObserver implements RequestObserver {
	
	private final GetPlanningPokerSessionController controller;
	
	/**
	 * Constructor for GetPlanningPokerSessionRequestObserver.
	 * @param controller GetPlanningPokerSessionController
	 */
	public GetPlanningPokerSessionRequestObserver(GetPlanningPokerSessionController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the messages out of the response body and pass them to the controller
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
	 * #responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		final PlanningPokerSession[] messages =
		                PlanningPokerSession.fromJsonArray(iReq.getResponse().getBody());
		controller.receivedMessages(messages);
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
	 * #responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
		
	}



}
