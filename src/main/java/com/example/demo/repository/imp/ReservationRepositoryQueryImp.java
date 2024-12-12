package com.example.demo.repository.imp;

import com.example.demo.entity.QItem;
import com.example.demo.entity.QReservation;
import com.example.demo.entity.QUser;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.ReservationRepositoryQuery;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ReservationRepositoryQueryImp implements ReservationRepositoryQuery {
    private final JPAQueryFactory query;

    public ReservationRepositoryQueryImp(JPAQueryFactory jpaQueryFactory) {
        this.query = jpaQueryFactory;
    }



    @Override
    public List<Reservation> searchReservation(Long userId, Long itemId) {

        QReservation reservation = QReservation.reservation;
        BooleanBuilder builder = new BooleanBuilder();

        if(userId != null) {
            builder.and(reservation.user.id.eq(userId));
        }
        if(itemId != null) {
            builder.and(reservation.item.id.eq(itemId));
        }
        return query
                .select(reservation)
                .from(reservation)
                .leftJoin(reservation.item, QItem.item)
                .fetchJoin()
                .leftJoin(reservation.user, QUser.user)
                .fetchJoin()
                .where(builder)
                .fetch();
    }




}
