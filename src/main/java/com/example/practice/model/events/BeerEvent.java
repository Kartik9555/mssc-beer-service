package com.example.practice.model.events;

import com.example.practice.model.BeerDTO;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {
    private static final long serialVersionUID = -7794418610800266635L;
    private BeerDTO beerDTO;
}
