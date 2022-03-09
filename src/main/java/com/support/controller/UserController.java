package com.support.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.support.entity.Comment;
import com.support.entity.Ticket;
import com.support.exception.ResourceNotFoundException;
import com.support.repository.CategoryRepository;
import com.support.repository.PrioriryRepository;
import com.support.repository.StatusRepository;
import com.support.repository.SubCategoryRepository;
import com.support.service.impl.CommentServiceImpl;
import com.support.service.impl.TicketServiceImpl;
import com.support.service.impl.UserServiceIMpl;

import response.ResponseModel;
import response.ResponseModel1;

@RestController
@RequestMapping("/api/user")
public class UserController {

	static String url = "http://localhost:8080/api/user/ticket/";

    private final  UserServiceIMpl userServiceImpl;

	private final  TicketServiceImpl ticketServiceImpl;

	private final  CommentServiceImpl commentServiceImpl;

	private final  CategoryRepository categoryRepo;

	private final  SubCategoryRepository subCategoryRepo;

	private final  StatusRepository statusRepo;

	private final  PrioriryRepository prioriryRepo;

	@Autowired
	public UserController(UserServiceIMpl userServiceImpl, TicketServiceImpl ticketServiceImpl,
			CommentServiceImpl commentServiceImpl, CategoryRepository categoryRepo,
			SubCategoryRepository subCategoryRepo, StatusRepository statusRepo, PrioriryRepository prioriryRepo) {
		super();
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


    // Create New Ticket
    @PostMapping("/ticket/add/")
    public ResponseEntity<?> createTicket(@RequestParam("userid") final long userid, @RequestBody Ticket ticket) {
        if (!userServiceImpl.getUserById(userid).isPresent())
            return new ResponseEntity<>(new ResponseModel("User " + userid + " Not Exists"), HttpStatus.NOT_FOUND);
        
		if (!subCategoryRepo.findById((long) ticket.getSub_category_id()).isPresent() || !(subCategoryRepo
				.findById((long) ticket.getSub_category_id()).get().getSub_category_id() == ticket.getCategory_id()))
			 return new ResponseEntity<>(new ResponseModel("Invalid Category Id or Sub Category Id"), HttpStatus.NOT_FOUND);
        	
        ticket.setReported_id(userid);
		ticket.setStatus_id(1);
        ticket.setCreate_datetime(getTimeNow());
        ticket.setLast_modified_datetime(getTimeNow());
        
        ticketServiceImpl.createTicket(ticket);

        userServiceImpl.getUserById(userid).map(user -> {
            ticket.setUser(user);
            System.out.println(user);
            return ticketServiceImpl.createTicket(ticket);
        }).orElseThrow(() -> new ResourceNotFoundException("Incorrect User Id = " + userid));

        return new ResponseEntity<>(
				new ResponseModel1(ticket.getTicket_id() + " Created Successfull",
						url + "?ticketid=" + ticket.getTicket_id()),
                HttpStatus.CREATED);
    }

    // Get Ticket List for User
    @GetMapping("/tickets/")
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
    public ResponseEntity<?> findTicketById(@RequestParam("ticketid") long ticketid) throws Exception {
        if (ticketServiceImpl.getTicketById(ticketid).isEmpty()) {
            return new ResponseEntity<>(new ResponseModel("Invalid Ticket Id"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketServiceImpl.getTicketById(ticketid).get(), HttpStatus.FOUND);
    }

    // Comment APIs
    // Get Comments by Ticket Id
	@GetMapping("/ticket/comments/")
	public ResponseEntity<?> getAllComments(@RequestParam("ticketid") long ticketid,
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
        return new ResponseEntity<>(new ResponseModel("Successfully Added Comment on Ticket" + ticketid), HttpStatus.CREATED);
    }
}
