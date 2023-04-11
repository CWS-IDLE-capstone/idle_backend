package com.idle.idle_backend.walk.entity;

import com.idle.idle_backend.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Walk {

    @Id
    @GeneratedValue
    @Column(name = "WALK_ID")
    private Long id;

    private Float distance;

    private String routeImage;

    private String startTime;

    private String finishTime;

    private String energyFinishTime;

    private Long energyFinishDistance;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
