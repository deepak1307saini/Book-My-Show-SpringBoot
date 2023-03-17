/**
 *
 */
package com.example.BookMyShowSpringBootApplication.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "shows")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "cinemaHall_Id", nullable = false)
    @JsonIgnore
    private CinemaHall cinemaHall;


    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ShowSeat> showSeats = new ArrayList<>();

}