package com.idle.idle_backend.walk.Entity;

import com.idle.idle_backend.user.domain.User;
import lombok.*;

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

    private String routeImage;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private LocalDateTime energyFinishTime;

    private Long energyFinishDistance;

    private Long distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
