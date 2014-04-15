package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;


public class PlanningPokerSession extends AbstractModel {
	/**
	 * Members of type PlanningPokerSession
	 */
	private int id;
	private String name;
	private String Description;
	private Date date;
	private String moderator;
	private String deck;


	private int hour;
	private int min;

	private List<RequirementEstimate> RequirementEstimates;
	private SessionType Type;
	
	// True if the game is active and ready to join
	private boolean isActive;

	// True if the game is complete and cannot be played 
	private boolean isComplete;

	private Date completionTime;

	/**
	 * This class acts as a builder for {@code PlanningPokerSession} because of the number
	 * of parameters to create a {@code PlanningPokerSession} object.
	 */
	public static class Builder {

		int id = -1;
		String name = "";
		String description = "";
		Date date = null; 
		int hour = -1;
		int min = -1;
		List<RequirementEstimate> requirementEstimations = null;
		SessionType type = null;
		boolean active = false;
		boolean complete = false;
		String moderator = "";
		String deck = "";

		/**
		 * Create a Builder
		 * @param id The id
		 */
		public Builder(int id) {
			this.id = id;
		}

		/**
		 * Set the name
		 * @param name The name
		 * @return This builder
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Set the description
		 * @param description The description
		 * @return This builder
		 */
		public Builder description(String description) {
			this.description = description;
			return this;
		}

		/**
		 * Set the date
		 * @param date The date
		 * @return This builder
		 */
		public Builder date(Date date) {
			this.date = date;
			return this;
		}

		/**
		 * Set the hour
		 * @param hour The hour
		 * @return This builder
		 */
		public Builder hour(int hour) {
			this.hour = hour;
			return this;
		}

		/**
		 * Set the minute
		 * @param min The minute
		 * @return This builder
		 */
		public Builder minute(int min) {
			this.min = min;
			return this;
		}

		/**
		 * Set the requirements to estimate
		 * @param reqEsts The list of requirements
		 * @return This builder
		 */
		public Builder requirementEstimates(List<RequirementEstimate> reqEsts) {
			this.requirementEstimations = reqEsts;
			return this;
		}

		/**
		 * Set the session type
		 * @param type The type
		 * @return Thils builder
		 */
		public Builder sessionType(SessionType type) {
			this.type = type;
			return this;
		}

		/**
		 * Set whether or not the session is active
		 * @param active True if the session is active
		 * @return This builder
		 */
		public Builder active(boolean active) {
			this.active = active;
			return this;
		}

		/**
		 * Set whether or not the session is complete
		 * @param complete True if the session is complete
		 * @return This builder
		 */
		public Builder complete(boolean complete) {
			this.complete = complete;
			return this;
		}

		/**
		 * Set the moderator
		 * @param moderator the moderator
		 * @return This builder
		 */
		public Builder moderator(String moderator) {
			this.moderator = moderator;
			return this;
		}

		/**
		 * Set the deck (via name)
		 * @param deck The deck
		 * @return This builder
		 */
		public Builder deck(String deck) {
			this.deck = deck;
			return this;
		}
		
		/**
		 * Create the {@code PlanningPokerSession}
		 * @return A {@code PlanningPokerSession} object represented by this builder
		 */
		public PlanningPokerSession build() {
			return new PlanningPokerSession(this);
		}
	}

	public Date getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	/**
	 * Generated getters and setters via Eclipse
	 */
	public int getID() 
	{
		return id;
	}

	public void setID(int iD) 
	{
		id = iD;
	}

	public int getHour() 
	{
		return hour;
	}

	public void setHour(int hour) 
	{
		this.hour = hour;
	}

	public int getMin() 
	{
		return min;
	}

	public void setMin(int min) 
	{
		this.min = min;
	}

	public void setDescription(String description) 
	{
		Description = description;
	}

	public String getName() 
	{
		return name;
	}


	public String getDescription() 
	{
		return Description;
	}

	public Date getDate() 
	{
		return date;
	}

	/**
	 * Get if the date represented by this session is in the past
	 * @return true if in the past, false otherwise
	 */
	public boolean isDateInPast() {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		Calendar thisDate = Calendar.getInstance();
		thisDate.setTime(date);
		return now.compareTo(thisDate) > 0;
	}

	public String getModerator(){
		return moderator;
	}

	public void setModerator(String newModerator){
		this.moderator = newModerator;
	}

	public void setDate(Date date) 
	{
		this.date = date;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public SessionType getType() 
	{
		return Type;
	}

	public void setType(SessionType type) 
	{
		Type = type;
	}

	public boolean isActive() 
	{
		return isActive;
	}

	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
	}

	public boolean isComplete() 
	{
		return isComplete;
	}

	public void setComplete(boolean isComplete) 
	{
		this.isComplete = isComplete;
	}

	/**
	 * Default constructor for a planning Poker session
	 */
	 public PlanningPokerSession() {
		 super();
		 id = 0;
		 name = "";
		 Description = "";
		 moderator = "";
		 date = new Date();
		 hour = 0;
		 min = 0;
		 RequirementEstimates = new ArrayList<RequirementEstimate>();
		 Type = SessionType.REALTIME;
		 isActive = false;
		 isComplete = false;
		 setDeck("-None-");
	 }

	 /**
	  * constructor for a planning poker Session
	  */

	 public PlanningPokerSession(Builder b) 
	 {
		 id = b.id;
		 Description = b.description;
		 this.moderator = b.moderator;
		 this.date = b.date;
		 hour = b.hour;
		 min = b.min;
		 name = b.name;
		 RequirementEstimates = b.requirementEstimations;
		 Type = b.type;
		 isActive = b.active;
		 this.deck = b.deck;

		 // Prevents from adding an active, closed session
		 if (isActive) {
			 isComplete = false;
		 } else {
			 isComplete = b.complete;
		 }
	 }

	 public static PlanningPokerSession fromJson(String json) {
		 final Gson parser = new Gson();
		 return parser.fromJson(json, PlanningPokerSession.class);
	 }

	 public static PlanningPokerSession[] fromJsonArray(String json) {
		 final Gson parser = new Gson();
		 return parser.fromJson(json, PlanningPokerSession[].class);
	 }

	 public List<RequirementEstimate> getRequirements() 
	 {
		 return RequirementEstimates;
	 }

	 /**
	  * Default Model methods: not required
	  */
	 @Override
	 public void delete() {
		 // TODO Auto-generated method stub
	 }

	 @Override
	 public Boolean identify(Object arg0) {
		 // TODO Auto-generated method stub
		 return null;
	 }

	 @Override
	 public void save() {
		 // TODO Auto-generated method stub
	 }

	 @Override
	 /**
	  * Converts object to JSON for going into the network and database
	  */
	 public String toJSON() {
		 return new Gson().toJson(this, PlanningPokerSession.class);
	 }

	 public void copyFrom(PlanningPokerSession updatedSession) {
		 this.id = updatedSession.id;
		 this.name = updatedSession.name;
		 this.Description = updatedSession.Description;
		 this.date = updatedSession.date;
		 this.deck = updatedSession.deck;
		 this.hour = updatedSession.hour;
		 this.min = updatedSession.min;
		 this.RequirementEstimates = updatedSession.RequirementEstimates;
		 this.Type = updatedSession.Type;
		 this.isActive = updatedSession.isActive;
		 this.isComplete = updatedSession.isComplete;
		 this.moderator = updatedSession.moderator;
		 this.completionTime = updatedSession.completionTime;
	 }

	 /* (non-Javadoc)
	  * @see java.lang.Object#hashCode()
	  */
	 @Override
	 public int hashCode() {
		 final int prime = 31;
		 int result = 1;
		 result = prime * result
				 + ((Description == null) ? 0 : Description.hashCode());
		 result = prime * result + hour;
		 result = prime * result + id;
		 result = prime * result + min;
		 result = prime * result + ((name == null) ? 0 : name.hashCode());
		 result = prime
				 * result
				 + ((RequirementEstimates == null) ? 0 : RequirementEstimates
						 .hashCode());
		 result = prime * result + ((Type == null) ? 0 : Type.hashCode());
		 result = prime * result + ((date == null) ? 0 : date.hashCode());
		 result = prime * result + (isActive ? 1231 : 1237);
		 result = prime * result + (isComplete ? 1231 : 1237);
		 result = prime * result + ((deck == null) ? 0 : deck.hashCode());
		 return result;
	 }

	 /* (non-Javadoc)
	  * @see java.lang.Object#equals(java.lang.Object)
	  */
	 @Override
	 public boolean equals(Object obj) {
		 if (this == obj) {
			 return true;
		 }
		 if (obj == null) {
			 return false;
		 }
		 if (!(obj instanceof PlanningPokerSession)) {
			 return false;
		 }
		 PlanningPokerSession other = (PlanningPokerSession) obj;
		 if (!Description.equals(other.Description)) {
			 return false;
		 }
		 if (hour != other.hour) {
			 return false;
		 }
		 if (id != other.id) {
			 return false;
		 }

		 if (deck == null) {
			 if (other.deck != null) {
				 return false;
			 }
		 }

		 if (!(other.deck == null && deck == null)) {		
			 //check if equal
			 if (!this.deck.equals(other.deck)) {
				 return false;
			 }
		 }

		 if (min != other.min) {
			 return false;
		 }
		 if (!name.equals(other.name)) {
			 return false;
		 }
		 boolean reqsEqual = true;
		 if (RequirementEstimates.size() == other.RequirementEstimates.size()) {
			 for (int i = 0; i < RequirementEstimates.size(); i++) {
				 if (other.RequirementEstimates.get(i).getId() != RequirementEstimates.get(i).getId()) {
					 reqsEqual = false;
				 }
			 }
		 } else {
			 reqsEqual = false;
		 }
		 if (!reqsEqual) return false;
		 if (Type != other.Type) {
			 return false;
		 }
		 if (Type == SessionType.DISTRIBUTED) {
			 if (!date.equals(other.date)) {
				 return false;
			 }
		 }
		 if (isActive != other.isActive) {
			 return false;
		 }
		 if (isComplete != other.isComplete) {
			 return false;
		 }
		 if (completionTime != null) {
			 if (!completionTime.equals(other.completionTime)) {
				 return false;
			 }
		 } else if (other.completionTime != null) {
			 return false;
		 }
		 return true;
	 }


	 /**
	  * @return the deck
	  */
	 public String getDeck() {
		 return deck;
	 }

	 /**
	  * @param deck the deck to set
	  */
	 public void setDeck(String deck) {
		 this.deck = deck;
	 }

}
