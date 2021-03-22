package com.freeefly.msscbrewery.web.module;


import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Customer {
    private UUID id;
    private String name;
}
