package crm08.entity;

public class TaskEntity {
	private int id;
	private String name;
	private String start_date;
	private String end_date;
	private UserEntity user;
	private JobEntity job;
	private StatusEntity status;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public JobEntity getJob() {
		return job;
	}
	public void setJob(JobEntity job) {
		this.job = job;
	}
	public StatusEntity getStatus() {
		return status;
	}
	public void setStatus(StatusEntity status) {
		this.status = status;
	}

}
