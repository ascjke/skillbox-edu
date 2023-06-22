package ru.borisov.skillboxedu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

    private String lastName;
    private String firstName;
    private String middleName;

    @Enumerated(EnumType.STRING)
    private Male male;

    private LocalDate birthDate;
    private String city;
    private String profileImage;
    private String bio;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> hardSkills;

    private String phone;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Follower> followers;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Following> followings;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
