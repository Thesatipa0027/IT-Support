package com.support.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.support.entity.Admin;
import com.support.entity.Comment;
import com.support.entity.Ticket;
import com.support.entity.User;
import com.support.repository.CategoryRepository;
import com.support.repository.PrioriryRepository;
import com.support.repository.StatusRepository;
import com.support.repository.SubCategoryRepository;
import com.support.service.AdminService;
import com.support.service.impl.CommentServiceImpl;
import com.support.service.impl.TicketServiceImpl;
import com.support.service.impl.UserServiceIMpl;

import response.ResponseModel;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	static String url = "http://localhost:8080/api/admin/ticket/?ticketid=";

	private final AdminService adminServiceimpl;

    private final UserServiceIMpl userServiceImpl;

    private final TicketServiceImpl ticketServiceImpl;

    private final CommentServiceImpl commentServiceImpl;

    private final CategoryRepository categoryRepo;

    private final SubCategoryRepository subCategoryRepo;

    private final StatusRepository statusRepo;

    private final PrioriryRepository prioriryRepo;

	public AdminController(AdminService adminServiceimpl, UserServiceIMpl userServiceImpl,
                           TicketServiceImpl ticketServiceImpl, CommentServiceImpl commentServiceImpl,
                           CategoryRepository categoryRepo, SubCategoryRepository subCategoryRepo,
                           StatusRepository statusRepo, PrioriryRepository prioriryRepo) {
		this.adminServiceimpl = adminServiceimpl;
        this.userServiceImpl = userServiceImpl;
        this.ticketServiceImpl = ticketServiceImpl;
        this.commentServiceImpl = commentServiceImpl;
        this.categoryRepo = categoryRepo;
        this.subCategoryRepo = subCategoryRepo;
        this.statusRepo = statusRepo;
        this.prioriryRepo = prioriryRepo;
    }

    public String getTimeNow() {
        final LocalDateTime now = LocalDateTime.now();
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        final String formatDateTime = now.format(format);
        return formatDateTime;
    }



    // Create User
    @PostMapping("/user/create")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
		final long count = userServiceImpl.userList().stream().filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .count();
        if (count != 0)
            return new ResponseEntity<>(
                    new ResponseModel("User With Email ID " + "'" + user.getEmail() + "'" + " Already Exists"),
                    HttpStatus.NOT_FOUND);

		final long count1 = userServiceImpl.userList().stream().filter(user1 -> user1.getUser_id() == user.getUser_id())
				.count();
		if (count1 != 0)
			return new ResponseEntity<>(
					new ResponseModel("User With ID " + "'" + user.getUser_id() + "'" + " Already Exists"),
					HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(userServiceImpl.saveUser(user), HttpStatus.CREATED);
    }

    // Get User List
    @GetMapping("/users")
    public ResponseEntity<?> viewUsers() {
        if (userServiceImpl.userList().isEmpty())
            return new ResponseEntity<>(new ResponseModel("No Users Found!"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userServiceImpl.userList(), HttpStatus.FOUND);
    }

    // Get User by ID
	@GetMapping("/user/")
	public ResponseEntity<?> getUserById(@RequestParam("userId") long userId) {
		if (userServiceImpl.getUserById(userId).isEmpty())
            return new ResponseEntity<>(new ResponseModel("No data Found!"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(userServiceImpl.getUserById(userId).get(), HttpStatus.FOUND);
    }

    // Delete User By ID
    @DeleteMapping("/users/")
    public ResponseEntity<?> deleteUsers(@RequestParam long id) {
        if (userServiceImpl.getUserById(id).isEmpty())
            return new ResponseEntity<>(new ResponseModel("No data Found!"), HttpStatus.FOUND);
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(new ResponseModel("User " + id + " Deleted Succesfully!"), HttpStatus.FOUND);
    }

    // Ticket APIs
	// Get Ticket list
    @GetMapping("/tickets")
    public ResponseEntity<?> getAllTickets() {
        if (ticketServiceImpl.ticketList().isEmpty()) {
            return new ResponseEntity<>(new ResponseModel("No data Found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketServiceImpl.ticketList(), HttpStatus.OK);
    }

	// Get Ticket List for User
	@GetMapping("/users/tickets/")
    public ResponseEntity<?> showTickets(@RequestParam("userid") long userid) {
        if (!userServiceImpl.getUserById(userid).isPresent())
            return new ResponseEntity<>(new ResponseModel("User " + userid + " Not Exists"), HttpStatus.NOT_FOUND);
        List<Ticket> list = ticketServiceImpl.ticketList();
        list = list.stream().filter(ticket -> ticket.getUser().getUser_id() == userid).collect(Collectors.toList());
        if (list.isEmpty())
            return new ResponseEntity<>(new ResponseModel("No Tickets Found for User:" + userid), HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Ticket>>(list, HttpStatus.FOUND);
    }

    // Get Ticket By ID
	@GetMapping("/ticket/")
	public ResponseEntity<?> findTicketById(@RequestParam("ticketId") int ticketId) throws Exception {
		if (ticketServiceImpl.getTicketById(ticketId).isEmpty()) {
            return new ResponseEntity<>(new ResponseModel("Invalid Ticket Id"), HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(ticketServiceImpl.getTicketById(ticketId).get(),
				HttpStatus.FOUND);
    }

    // Set Assignee to Ticket by ID
	@PutMapping("/ticket/setassignee/")
	public ResponseEntity<?> setAssignee(@RequestParam("userid") int userid, @RequestParam("ticketid") int ticketid,
			@RequestBody Admin admin) {

		if (ticketServiceImpl.getTicketById(ticketid).isEmpty())
			return new ResponseEntity<>(new ResponseModel("Incorrect Ticket Id or Incorrect User Id"),
                    HttpStatus.NOT_FOUND);
		if (!adminServiceimpl.getAdmin(admin.getAdmin_id()).isPresent()) {
			return new ResponseEntity<>(new ResponseModel("Incorrect Ticket Id or Incorrect User Id2"),
					HttpStatus.NOT_FOUND);
		}
		admin = adminServiceimpl.getAdmin(admin.getAdmin_id()).get();

		Ticket ticket1 = ticketServiceImpl.getTicketById(ticketid).get();
		ticket1.setAssignee(admin);
        ticket1.setLast_modified_datetime(getTimeNow());

		return new ResponseEntity<>(ticketServiceImpl.createTicket(ticket1), HttpStatus.OK);
    }

	// change Ticket status by ID
	@PutMapping("/ticket/changestatus/")
	public ResponseEntity<?> changeStatus(@RequestParam("ticketId") int ticketId, @RequestBody Ticket ticket) {
		if (!ticketServiceImpl.getTicketById(ticketId).isPresent()) {
			return new ResponseEntity<>(new ResponseModel("Incorrect Ticket Id or Incorrect User Id"),
					HttpStatus.NOT_FOUND);
		}
		if (!statusRepo.findById((long) ticket.getStatus_id()).isPresent()
				|| statusRepo.findById((long) ticket.getStatus_id()).get().getStatus_id() > ticket.getStatus_id()) {
			return new ResponseEntity<>(new ResponseModel("Incorrect Status Id"), HttpStatus.NOT_FOUND);
		}
		Ticket ticket1 = ticketServiceImpl.getTicketById(ticketId).get();

		ticket1.setStatus_id(ticket.getStatus_id());
		ticket1.setLast_modified_datetime(getTimeNow());
		return new ResponseEntity<>(ticketServiceImpl.createTicket(ticket1), HttpStatus.OK);
	}

    // Get Comments by Ticket Id and User ID
	@GetMapping("/tickets/comments/")
    public ResponseEntity<?> getComments(@RequestParam("ticketid") long ticketid,
                                         @RequestParam("userid") long userid) {

        if (!(userServiceImpl.getUserById(userid).isPresent() && ticketServiceImpl.getTicketById(ticketid).isPresent()))
            return new ResponseEntity<>(new ResponseModel("Invalid Ticket Id Or User Id"), HttpStatus.NOT_FOUND);

        List<Comment> list = commentServiceImpl.getAllComments();
        list = list.stream().filter(t -> t.getTicket().getTicket_id() == ticketid).collect(Collectors.toList());
        if (list.isEmpty())
            return new ResponseEntity<>(new ResponseModel("No comments found for ticket " + ticketid),
                    HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }

	// Add Comments
	@PostMapping("/ticket/comment/add/")
	public ResponseEntity<?> addComment(@RequestParam("userid") long userid, @RequestParam("ticketid") long ticketid,
			@RequestBody Comment comment) {

		if (!(userServiceImpl.getUserById(userid).isPresent() && ticketServiceImpl.getTicketById(ticketid).isPresent()))
			return new ResponseEntity<>(new ResponseModel("Invalid Ticket Id Or User Id"), HttpStatus.NOT_FOUND);

		final List<Comment> list = ticketServiceImpl.getTicketById(ticketid).get().getCommentList();
		list.add(comment);
		final Ticket ticket = ticketServiceImpl.getTicketById(ticketid).get();
		ticket.setLast_modified_datetime(getTimeNow());
		comment.setTicket(ticket);
		comment.setUser(userServiceImpl.getUserById(userid).get());
		commentServiceImpl.addComment(comment);
		return new ResponseEntity<>(new ResponseModel("Successfully Added Comment on Ticket" + ticketid),
				HttpStatus.CREATED);
	}
}
