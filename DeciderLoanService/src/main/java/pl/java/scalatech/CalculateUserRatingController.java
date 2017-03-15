package pl.java.scalatech;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateUserRatingController {

    private static float getPercentage(float n, float total) {
        float proportion = n / total;
        return proportion * 100;
    }
    
}
