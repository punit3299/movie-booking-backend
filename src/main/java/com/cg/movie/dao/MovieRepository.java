package com.cg.movie.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Movie;
import com.cg.movie.response.GenreResponse;

@Transactional
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("select m from Movie m order by m.movieRating desc")
	List<Movie> topThreeMovies();

	@Query("SELECT movie from Movie movie WHERE movie.movieId=?1 AND movie.status=1")
	Movie findByMovieId(long movieId);

	@Query("select movie from Movie movie WHERE movie.movieName=?1 AND movie.status=1 ")
	Movie findMovieByName(String movieName);

	@Modifying
	@Query("UPDATE Movie movie SET movie.status=0 WHERE movie.movieId=?1")
	void deleteMovieById(long movieId);

	@Query("SELECT movie FROM Movie movie WHERE movie.status = 1")
	List<Movie> findAllMovies();

	@Query(value = "select movie_genre as genre,count(movie_id) as count from movie_table group by movie_genre;", nativeQuery = true)
	List<GenreResponse> genrewiseMoviesCount();

}
