package ru.est0y.diceChess.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Data
@AllArgsConstructor
@Document
public class Move {
    @NotNull
    @Valid
    private final Square from;

    @NotNull
    @Valid
    private final Square to;

    private final AbstractPiece promotion;

    public Move(@JsonProperty("from") Square from, @JsonProperty("to") Square to) {
        this.from = from;
        this.to = to;
        this.promotion = null;
    }

    public Optional<AbstractPiece> getPromotion() {
        return Optional.ofNullable(promotion);
    }
}
