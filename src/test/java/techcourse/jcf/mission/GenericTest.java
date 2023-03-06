package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GenericTest {
    @Test
    void mission1() {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    void mission2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.fromArrayToList(arrays);

        assertThat(values.size()).isEqualTo(2);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.get(1)).isEqualTo("second");
    }

    @Test
    void mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    void mission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertThat(filteredDoubleValues.contains(-0.1)).isFalse();
        assertThat(filteredIntValues.contains(-10)).isFalse();
    }

    @Test
    void mission5() {
        final var laserPrinter = new LaserPrinter();
        final SimpleList<Printer> printers = new SimpleArrayList<>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        assertThat(printers.get(0)).isEqualTo(laserPrinter);
    }
}
