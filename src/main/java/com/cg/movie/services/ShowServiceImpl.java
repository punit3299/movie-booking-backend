package com.cg.movie.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Language;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.InValidDataEntryException;
import com.cg.movie.exception.MovieDoesntExistException;
import com.cg.movie.exception.ShowDoesntExistException;
import com.cg.movie.exception.TheatreNotFoundException;
import com.cg.movie.request.ShowRequestVO;
import com.cg.movie.response.MovieResponseVO;
import com.cg.movie.response.ShowResponseVO;
import com.cg.movie.validator.ShowValidator;

/********************************************************************************
 * 
 * @author Prabhjot Description :It provides the service for add show for
 *         theatre,delete show and view all shows.
 * 
 *         created by : Prabhjot , 9 August 2020
 *
 ********************************************************************************/

@Service
public class ShowServiceImpl implements IShowService {
	@Autowired
	private ShowRepository showRepo;

	@Autowired
	private TheatreRepository theatreRepo;

	@Autowired
	private ShowValidator showValidator;

	private Logger logger = Logger.getLogger(getClass());

	/********************************************************************************
	 * 
	 * Method : addNewShow Description: for adding the show.
	 * 
	 * @param theatreId Theatre theatreId
	 * @param screenId  Screen screenId
	 * @param movieId   Movie movieId
	 * @param Show      Show show
	 * 
	 * @return show Id i.e showId of show added.
	 * 
	 *         Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Long addNewShow(ShowRequestVO showRequestVO) throws InValidDataEntryException {

		Movie movie = showValidator.validateMovieName(showRequestVO.getMovieName());
		Theatre theatre = showValidator.validateTheatreId(showRequestVO.getTheatreId());
		Screen screen = showValidator.validateScreenId(showRequestVO.getScreenId());

		if (movie.getMovieReleaseDate().before(showRequestVO.getShowStartTime())) {
			throw new InValidDataEntryException("Show cannot be created before movie release date");
		}

		Set<String> languages = movie.getLanguageList().stream().map(Language::getLanguageName)
				.collect(Collectors.toSet());

		if (!languages.contains(showRequestVO.getShowLanguage())) {
			throw new InValidDataEntryException(movie.getMovieName() + " is not available in " + showRequestVO.getShowLanguage());
		}

		Show show = new Show();
		show.setShowEndTime(showRequestVO.getShowEndTime());
		show.setShowStartTime(showRequestVO.getShowStartTime());
		show.setShowLanguage(showRequestVO.getShowLanguage());
		show.setShowName(showRequestVO.getShowName());

		showValidator.validateShowTimePeriod(show.getShowStartTime(), show.getShowEndTime(), screen.getScreenId());
		show.setStatus(true);
		show.setMovie(movie);
		show.setTheatre(theatre);
		show.setScreen(screen);

		Show addShow = showRepo.save(show);
		return addShow.getShowId();
	}

	/********************************************************************************
	 * 
	 * Method : deleteShowById
	 * 
	 * Description: for deleting the show by changing the show status to true.
	 * 
	 * @param : showId Show showId
	 * 
	 * @throw ShowDoesntExistException : It is raised when showId doesnt exist.
	 * 
	 *        Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public void deleteShowById(long showId) {
		if (showRepo.existsById(showId)) {
			showRepo.deleteShowById(showId);
		} else
			throw new ShowDoesntExistException("Show with" + showId + "doesn't exist");
	}

	/********************************************************************************
	 * 
	 * Method : getAllShow Description: for fetching the show of theatre.
	 * 
	 * @param theatreId
	 * 
	 *                  Theatre theatreId
	 * 
	 * @return show Set i.e showList1
	 * 
	 * @throws : when theatreId is not found then TheatreNotFoundException is
	 *           raised.
	 * 
	 *           Created by: Prabhjot ,9 August 2020
	 * 
	 **********************************************************************************/

	@Override
	public Set<ShowResponseVO> getAllShow(Long theatreId) {

		if (theatreRepo.existsById(theatreId)) {
			List<Show> showList = showRepo.findAllShows(theatreId);
			Set<ShowResponseVO> showResponses = new HashSet<>();
			for (Show show : showList) {
				ShowResponseVO showResponse = new ShowResponseVO();
				showResponse.setShowId(show.getShowId());
				showResponse.setMovieName(show.getMovie().getMovieName());
				showResponse.setScreenName(show.getScreen().getScreenName());
				showResponse.setTheatreName(show.getTheatre().getTheatreName());
				showResponse.setShowStartTime(show.getShowStartTime());
				showResponse.setShowEndTime(show.getShowEndTime());
				showResponse.setShowLanguage(show.getShowLanguage());
				showResponse.setShowName(show.getShowName());
				showResponses.add(showResponse);
			}
			return showResponses;
		} else
			throw new TheatreNotFoundException("Theatre with id" + theatreId + "not found");
	}

	@Override
	public List<Show> getShowByMovieId(Long movieId) {
		Movie movie = showRepo.findMovieIdExist(movieId);
		if (movie == null) {
			logger.error("movie not found");
			throw new MovieDoesntExistException("Movie with id " + movieId + " doesnot exist");
		} else {
			List<Show> shows = showRepo.findShowByMovieId(movieId);
			if (shows == null || shows.isEmpty()) {
				logger.error("show not found");
				throw new ShowDoesntExistException("There are no shows available with movie id" + movieId);
			} else {
				logger.info("shows found successfully");
				return shows;
			}
		}

	}

	@Override
	public List<Show> getShowByTheatreId(Long theaterId) {
		Theatre theater = showRepo.findShowIdExist(theaterId);
		if (theater == null) {
			logger.error("movie not found");
			throw new TheatreNotFoundException("Movie with id " + theaterId + " doesnot exist");
		} else {
			List<Show> shows = showRepo.findShowByTheatreId(theaterId);
			if (shows == null || shows.isEmpty()) {
				logger.error("show not found");
				throw new ShowDoesntExistException("There are no shows available with theatre id" + theaterId);
			} else {
				logger.info("shows found successfully");
				return shows;
			}
		}

	}

	@Override
	public List<Show> getAllShows() {
		logger.info("shows found successfully");
		List<Show> show = showRepo.findAll();
		if (show == null || show.isEmpty()) {
			logger.error("shows not found");
			throw new ShowDoesntExistException("There are no shows available currently");
		}
		return show;
	}

	@Override
	public void findShowById(Long showId) {
		if (!showRepo.existsById(showId)) {
			throw new ShowDoesntExistException("Show doesnot exist");
		}

	}

}
