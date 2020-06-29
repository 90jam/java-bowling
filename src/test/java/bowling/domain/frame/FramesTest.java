package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @Test
    void createFramesTest() {
        Frames frames = Frames.create();
        assertThat(frames).isNotNull();
    }

    @Test
    void framesPitchTest() {
        Frames frames = Frames.create();
        Frames firstFrames = frames.pitch(Point.inputPoint(5));
        assertThat(firstFrames.size()).isEqualTo(1);
    }
}