package hameed.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name="restaurants", indexes = {
        @Index(name = "idx_is_active", columnList = "is_active"),
        @Index(name = "idx_is_featured", columnList = "is_featured"),
        @Index(name = "idx_location", columnList = "latitude, longitude"),
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_stall", columnList = "stall_id")

})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Builder.Default
    @Column(name = "stall_id", nullable = false)
    private Integer stallId = 0;

    // ─── basic info ───────────────────────────────────────────
    @NotBlank(message = "Restaurant name is required")
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "sku", unique = true)
    private String sku;

    // ─── location ─────────────────────────────────────────────
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "pincode", length = 20)
    private String pincode;

    @Column(name = "location_id")
    private String locationId;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Column(name = "latitude", precision = 10, scale = 8)
    private Double latitude;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Column(name = "longitude", precision = 11, scale = 8)
    private Double longitude;

    // ─── contact ──────────────────────────────────────────────
    @Column(name = "phone_no", length = 20)
    private String phone;

    // ─── media ────────────────────────────────────────────────
    @Column(name = "image")
    private String image;

    @Column(name = "placeholder_image")
    private String placeholderImage;

    @Column(name = "certificate")
    private String certificate;

    // ─── rating & pricing ─────────────────────────────────────
    @JdbcTypeCode(SqlTypes.DECIMAL)
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Column(name = "rating", precision = 3, scale = 2)
    private Double rating;

    @Column(name = "price_range", length = 50)
    private String priceRange;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Builder.Default
    @Column(name = "min_order_price", precision = 10, scale = 2, nullable = false)
    private Double minOrderPrice = 0.00;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Builder.Default
    @Column(name = "restaurant_charges", precision = 10, scale = 2)
    private Double restaurantCharges = 0.00;

    // ─── delivery config ──────────────────────────────────────
    @Column(name = "delivery_time")
    private Integer deliveryTime;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Builder.Default
    @Column(name = "delivery_charges", precision = 10, scale = 2)
    private Double deliveryCharges = 0.00;

    @Builder.Default
    @Column(name = "delivery_type", nullable = false)
    private Integer deliveryType = 0;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Builder.Default
    @Column(name = "delivery_radius",precision = 10, scale = 2, nullable = false)
    private Double deliveryRadius = 0.00;


    @Builder.Default
    @Column(name = "base_delivery_distance", nullable = false)
    private Integer baseDeliveryDistance = 0;
    @Column(name = "delivery_charge_type", length = 50)
    private String deliveryChargeType;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Builder.Default
    @Column(name = "base_delivery_charge", precision = 10, scale = 2, nullable = false)
    private Double baseDeliveryCharge = 0.00;

    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Builder.Default
    @Column(name = "extra_delivery_charge", precision = 10, scale = 2, nullable = false)
    private Double extraDeliveryCharge = 0.00;

    @Builder.Default
    @Column(name = "extra_delivery_distance", nullable = false)
    private Integer extraDeliveryDistance = 0;

    // ─── business rules ───────────────────────────────────────
    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Builder.Default
    @Column(name = "commission_rate", precision = 5, scale = 2, nullable = false)
    private Double commissionRate = 0.00;

    @Column(name = "schedule_data", columnDefinition = "JSON")
    private String scheduleData;

    @Builder.Default
    @Column(name = "order_column", nullable = false)
    private Integer orderColumn = 0;

    // ─── boolean flags ────────────────────────────────────────
    @Builder.Default
    @Column(name = "is_pure_veg", nullable = false)
    private Boolean isPureVeg = false;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Builder.Default
    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted = false;

    @Builder.Default
    @Column(name = "is_featured", nullable = false)
    private Boolean isFeatured = false;

    @Builder.Default
    @Column(name = "is_notifiable", nullable = false)
    private Boolean isNotifiable = false;

    @Builder.Default
    @Column(name = "auto_acceptable", nullable = false)
    private Boolean autoAcceptable = false;

    // ─── audit ────────────────────────────────────────────────
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // auto-set timestamps before save and update
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
