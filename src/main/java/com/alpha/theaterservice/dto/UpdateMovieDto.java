package com.alpha.theaterservice.dto;

public class UpdateMovieDto {
	
	    private String title;
	    private String newtitle;
	    private String description;
	    private String caste;
	    private Double duration;
	    private String language;
		public UpdateMovieDto( String title, String description, String caste, Double duration,
				String language,String newtitle) {
			super();
			
			this.title = title;
			this.newtitle=newtitle;
			this.description = description;
			this.caste = caste;
			this.duration = duration;
			this.language = language;
		}
		public String getNewtitle() {
			return newtitle;
		}
		public void setNewtitle(String newtitle) {
			this.newtitle = newtitle;
		}
		public UpdateMovieDto() {
			super();
		}
		
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCaste() {
			return caste;
		}
		public void setCaste(String caste) {
			this.caste = caste;
		}
		public Double getDuration() {
			return duration;
		}
		public void setDuration(Double duration) {
			this.duration = duration;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
	    
	    
}
