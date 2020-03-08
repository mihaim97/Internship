package com.mihai.project.library.entity.interntable;

import com.mihai.project.library.entity.request.RentRequest;

import javax.persistence.*;

@Entity
@Table(name = "pending")
public class Pending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_request_id")
    private RentRequest rentRequestId;

    @Column(name = "copy_stock_id")
    private int copyId;

    public Pending() {
    }

    public Pending(int id, RentRequest rentRequestId, int copyId) {
        this.id = id;
        this.rentRequestId = rentRequestId;
        this.copyId = copyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RentRequest getRentRequestId() {
        return rentRequestId;
    }

    public void setRentRequestId(RentRequest rentRequestId) {
        this.rentRequestId = rentRequestId;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }
}
