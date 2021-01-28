# CRBK IoT Project

## Koncept diagramu komunikacji komponentów
<p align="center">
  <img src="https://github.com/Crovitz/crbk-project/blob/main/crbk-c0.png?raw=true" />

1. Każde z urządzeń klienckich będzie komunikować się z centralką za pomocą sieci WiFi. Sieć ta tworzona jest przez Centralke i nie ma ona dostępu do Internetu. Sieć ta będzie mieć ukryty SSID oraz hasło WPA2. Każde z wyprodukowanych urządzeń będzie miało zahardkodowane dane tych sieci.
- pomiary będą wysyłane w jakikolwiek sposób aby otrzymała je centralka, może być plain textem, JSONem czy BSONem;
- dane będą wysyłane z interwałach czasowych 15/30/60 minut, zależnie od danego urządzenia;
2. Centralka będzie tworzyć sieć WiFi dla ESP oraz będzie się łączyć z Internetem za pomocą ethernetu. Aby mieć 2 aktywne połączenia istnieje obowiązek posiadania dwóch kart sieciowych. Trzeba rozważyć w jaki sposób będzie działać połączenie po RJ45. Centralka będzie działać stale, ponieważ będzie zasilana z sieci, USB lub PoE.
3. Centralka po otrzymaniu wiadomości od klienta formatuje tę wiadomość do formatu JSON i wysyła poprzez protokół MQTT:
- host: crbkproject.ddns.net
- port: 1883
- client-id: unikalny dla każdej z centralek
- login
- hasło
- topic: measurement
4. Aplikacja backend subskrybuje się na RabbitMQ na dany topic. Topici zbindowane są na dane kolejki RabbitMQ w celu późniejszych subskrypcji w oparciu o Exchange. W sytuacji niedostępności aplikacji backendowej, wiadomości czekają na kolejkach, w momencie dostępności serwisu wiadomości zostaną natychmiast skonsumowane.
5. Dane wiadomości przeprocesowywane są na konkretne obiekty persystentne, np. Temperature, Soil Moisture czy Pressure, a następnie zapisywane w bazie MongoDB w osobnych kolekcjach. Docelowo dojdzie jeszcze baza InfluxDB do analizy danych, a zapis do baz będzie odbywać się transakcyjnie. Api w oparciu o basic authentication daje możliwość odczytu dostępnych pomiarów i innych akcji.
</p>
