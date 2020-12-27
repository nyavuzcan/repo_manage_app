      package com.example.nk.entities;


      import javax.persistence.*;
      import java.time.LocalDate;
      import java.util.Date;
      import java.util.UUID;

      @Entity
      public class ConfirmationTokenEntity {
        public ConfirmationTokenEntity() {
        }


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String confirmationToken;

        private Date expiredDate;

        private UserEntity userEntity;

        public ConfirmationTokenEntity(UserEntity user) {
          this.userEntity = user;
          this.expiredDate = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
          this.confirmationToken = UUID.randomUUID().toString();
        }

        public Long getId() {
          return id;
        }

        public void setId(Long id) {
          this.id = id;
        }

        public String getConfirmationToken() {
          return confirmationToken;
        }

        public void setConfirmationToken(String confirmationToken) {
          this.confirmationToken = confirmationToken;
        }

        public Date getCreatedDate() {
          return expiredDate;
        }

        public void setCreatedDate(Date createdDate) {
          this.expiredDate = createdDate;
        }

        @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "user_id")
        public UserEntity getUser() {
          return userEntity;
        }

        public void setUser(UserEntity user) {
          this.userEntity = user;
        }
      }
