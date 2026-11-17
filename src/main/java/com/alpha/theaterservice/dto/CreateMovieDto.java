package com.alpha.theaterservice.dto;

public class CreateMovieDto {

	 private String title;

	    private String description;

	    private String caste;

	    private double duration;

	    private String language;

		public CreateMovieDto(String title, String description, String caste, double duration, String language) {
			super();
			this.title = title;
			this.description = description;
			this.caste = caste;
			this.duration = duration;
			this.language = language;
		}
		

		public CreateMovieDto() {
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

		public double getDuration() {
			return duration;
		}

		public void setDuration(double duration) {
			this.duration = duration;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		@Override
		public String toString() {
			return "CreateMovieDto [title=" + title + ", description=" + description + ", caste=" + caste
					+ ", duration=" + duration + ", language=" + language + "]";
		}
	    
}
