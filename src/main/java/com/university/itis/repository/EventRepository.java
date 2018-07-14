package com.university.itis.repository;


import com.university.itis.model.Event;
import com.university.itis.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByGroupIdInOrderByDateAsc(List<Long> courseIds);


//    @Query(value = "SELECT n FROM Event n WHERE n.date between ?1 and ?2 " +
//            "and (n.group.id in ?3) order by n.deadline asc ")
//    List<News> getNews(Date begin, Date end, List<Long> courseIds, Integer year);


    Page<Event> findAllByGroupInOrderByDateAsc(Collection<Group> groups, Pageable pageable);

    List<Event> findAllByGroupIdOrderByDateAsc(Long groupId);

    List<Event> findAllByDateIsLessThanEqualAndDateIsGreaterThanEqualOrderByDateAsc(Date start, Date end);

    List<Event> findAllByDateIsLessThanEqualAndDateIsGreaterThanEqualAndGroupInOrderByDateAsc(Date start, Date end,
                                                                                              List<Group> groups);

    List<Event> findAllByGroupInOrderByDateAsc(List<Group> groups);
}
