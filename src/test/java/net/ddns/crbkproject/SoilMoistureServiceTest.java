package net.ddns.crbkproject;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Soil moisture tests")
class SoilMoistureServiceTest extends BaseTest {

//    @Test
//    @DisplayName("Converting casted event to domain object")
//    void shouldConvertCastedEvent() throws JsonProcessingException {
//        // given
//        Message<?> message = prepareMessage();
//
//        // when
//        Set<Event> events = EventHandler.castEvents(message);
//        SoilMoistureEvent event = events.stream()
//                .filter(e -> e.getClass().getName().equalsIgnoreCase(SoilMoistureEvent.class.getName()))
//                .map(e -> (SoilMoistureEvent) e)
//                .findAny().get();
//        SoilMoisture soilMoisture = requireNonNull(mvcConversionService.convert(event, SoilMoisture.class));
//
//        // then
//        assertThat(soilMoisture.getPercent()).isEqualTo(52);
//        assertThat(soilMoisture.getMeasuredAt())
//                .isEqualTo(LocalDateTime.of(2020, 1, 1, 2, 0, 0, 0));
//    }
//
//    @Test
//    void contextLoads() {
//        assertThat(Boolean.TRUE).isTrue();
//    }
//
//    private Message<?> prepareMessage() {
//        String payload = "{\"dev\":\"hw-080\",\"sm\":\"52\",\"temp\":\"22\"}";
//        MutableMessageHeaders headers = new MutableMessageHeaders(Map.of(
//                "mqtt_receivedRetained", false,
//                "mqtt_id", 0,
//                "mqtt_duplicate", false,
//                "id", UUID.fromString("eeee0741-191b-5af9-76b8-487d948c6881"),
//                "mqtt_receivedTopic", "soil-moisture",
//                "mqtt_receivedQos", 0,
//                "timestamp", 1577844000000L));
//
//        return new GenericMessage<>(payload, headers);
//    }
}
