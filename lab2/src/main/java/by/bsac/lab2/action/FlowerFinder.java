package by.bsac.lab2.action;

import by.bsac.lab2.entity.CutFlower;

import java.util.List;
import java.util.Optional;

public class FlowerFinder {
    private static final int MAX_STEM_LENGTH = 88;
    private static final int MIN_STEM_LENGTH = 20;

    public static Optional<CutFlower> findFlowerByStemLength(List<CutFlower> flowers) {
        return flowers.stream()
                .filter(flower -> flower.getStemLength() >= MIN_STEM_LENGTH
                        && flower.getStemLength() <= MAX_STEM_LENGTH)
                .findFirst();
    }
}
