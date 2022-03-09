package response;

import org.springframework.beans.factory.annotation.Autowired;

import com.support.desc.DescriptionFetcher;
import com.support.entity.Ticket;

public class TicketResponse {

	private long ticket_id;

	private String category;

	private String sub_category;

	private String subject;

	private String priority;

	private String status;

	private String assignee;

	private String url;

	@Autowired
	private DescriptionFetcher fetcher;

	public TicketResponse(Ticket ticket, String url) {
		super();
		this.ticket_id = ticket.getTicket_id();
		this.category = fetcher.getCategoryDesById(ticket.getCategory_id());
		this.sub_category = fetcher.getSubCatagoryDescById(ticket.getSub_category_id());
		this.subject = ticket.getSubject();
		this.priority = fetcher.getPriorityDescById(ticket.getPriority_id());
		this.status = fetcher.getSatusDescById(ticket.getStatus_id());
		this.assignee = ticket.getAssignee().getName();
		this.url = this.url + ticket.getTicket_id();
	}

	public long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DescriptionFetcher getFetcher() {
		return fetcher;
	}

	public void setFetcher(DescriptionFetcher fetcher) {
		this.fetcher = fetcher;
	}

	public String getPriority() {
		return priority;
	}

	public String getStatus() {
		return status;
	}

}
