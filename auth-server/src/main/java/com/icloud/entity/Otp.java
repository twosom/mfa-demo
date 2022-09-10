package com.icloud.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private boolean validated;

    protected Otp() {
    }

    public Otp(User user, String code) {
        this.user = user;
        this.code = code;
    }

    public void updateCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
