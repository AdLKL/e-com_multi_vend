package com.ad.ecommerceMultivBackend.model;

import com.ad.ecommerceMultivBackend.domain.HomeCategorySection;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;

    private String name;

    private String categoryId;

    private HomeCategorySection section;
}
