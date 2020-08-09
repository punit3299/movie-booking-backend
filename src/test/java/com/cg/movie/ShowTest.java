package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.ShowRepository;
import com.cg.movie.entities.Show;
import com.cg.movie.services.ShowServiceImpl;


@SpringBootTest
public class ShowTest {
   
	@Autowired
	ShowServiceImpl showService;
	
	@MockBean
	ShowRepository showRepo;
	
	@Test
	public void addShowTest() {
		Show show =new Show(new Long(1), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker");
        when(showRepo.save(show)).thenReturn(show);	
        assertEquals(show, showService.addShow(show));
	}
	
	
	
    
}
