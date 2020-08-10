package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.entities.Screen;
import com.cg.movie.services.ScreenServiceImpl;

@SpringBootTest
public class ScreenTest {

	@Autowired
	ScreenServiceImpl screenService;
	
	@MockBean
	ScreenRepository screenRepo;
	
	@Test
	public void addScreenTest() {
		Screen screenDetails=new Screen((long)101,"Audi 1",80);
		when(screenRepo.save(screenDetails)).thenReturn(screenDetails);
		assertEquals(screenDetails,screenService.addScreen(101, screenDetails));
	}
	
	@Test
	public void getAllScreenTest()
	{
		when(screenRepo.findAll()).thenReturn(Stream.of(new Screen((long)101,"Audi 1",80),new Screen((long)102,"Audi 2",75)).collect(Collectors.toList()));
		assertEquals(2,screenService.getAllScreen((long)116).size());
	}
	
	@Test
	public void deleteScreenTest()
	{
//		when(screenRepo.existsById((long)101).thenReturn(true);
//		screenService.deleteScreen((long)101);
//		verify(screenRepo,times(1)).deleteScreenById(false, (long) 101);
	}
	
//	@Test
//	public void addSeatTest()
//	{
//		when(screenRepo.findById((long) 101).get()).thenReturn(screenRepo.save(new Screen((long)101,"Audi 1",0)));
//		assertEquals(75,screenService.addSeats((long)101, 75));
//	}
}
