# CRBK IoT Project

## Uruchomienie lokalne
Uruchomić dockerowy stack

    docker stack deploy -c ./docker-local.yml measurement

Wykonać polecenia:

    ./gradlew bootRun -Dspring.profiles.active=local

Otworzyć http://localhost:8080/swagger-ui.html

## Domena

Celem oprogramowania jest zbieranie danych z czujników IoT, np. czujniki temperatury, jakości powietrza, wilgotności gleby czy nasłonecznienia. Założeniem jest, iż czujniki będą wykorzystywać mikrokontrolery ESP8266. Czujnik składa się z urządzenia klienckiego zbierającego dane oraz centralki, do której może być podłączone wiele czujników. Komunikacja między nimi przebiega w prywatnej sieci WiFi dzięki temu mają odseparowaną sieć bez DHCP, co znacznie przyspiesza ich przyłączanie do sieci.

## Koncept diagramu komunikacji komponentów
<p align="center">
  <img src="https://github.com/Crovitz/crbk-project/blob/main/domain-iot-flow.png?raw=true" />

1. Każde z urządzeń klienckich będzie komunikować się z centralką za pomocą sieci WiFi. Sieć ta tworzona jest przez Centralke i nie ma ona dostępu do Internetu. Sieć ta będzie mieć ukryty SSID oraz hasło WPA2. Każde z wyprodukowanych urządzeń będzie miało zahardkodowane dane tych sieci.
- pomiary będą wysyłane w jakikolwiek sposób aby otrzymała je centralka, może być plain textem, JSONem czy BSONem;
- dane będą wysyłane z interwałach czasowych 15/30/60 minut, zależnie od danego urządzenia;
2. Centralka będzie tworzyć sieć WiFi dla ESP oraz będzie się łączyć z Internetem za pomocą ethernetu. Aby mieć 2 aktywne połączenia istnieje obowiązek posiadania dwóch kart sieciowych. Trzeba rozważyć w jaki sposób będzie działać połączenie po RJ45. Centralka będzie działać stale, ponieważ będzie zasilana z sieci, USB lub PoE.
3. Centralka po otrzymaniu wiadomości od klienta formatuje tę wiadomość do formatu JSON i wysyła do Api Gateway po REST. Api Gateway po otrzymaniu wiadomości wysyła ją poprzez protokół AMQP na kolejkę RabbitMQ.
4. Aplikacja backend subskrybuje się na RabbitMQ na dany exchange. W sytuacji niedostępności aplikacji backendowej, wiadomości czekają na kolejkach, w momencie dostępności serwisu wiadomości zostaną natychmiast skonsumowane.
5. Dane wiadomości przeprocesowywane są na konkretne obiekty persystentne, np. Temperature, Soil Moisture czy Pressure, a następnie zapisywane w bazie MongoDB w osobnych kolekcjach. Docelowo dojdzie jeszcze baza InfluxDB do analizy danych, a zapis do baz będzie odbywać się transakcyjnie. Api w oparciu autoryzacje OAuth2 daje możliwość odczytu dostępnych pomiarów i innych akcji.
</p>

## Architektura infrastrukturalna

Aplikacja budowana jest w architekturze mikroserwisowej. Każdy z serwisów musi być autonomiczny, skalowalny z wydzielonym bounded contextem. Dużą wagę przykłada się do IaC (Infrastructure as a code), w związku z tym wszystko oparte jest o Docker Swarm, gdzie widoczności między komponentami często określa się za pomocą separacji sieci. Dobór komponentów nastepuje w myśl zasady "Vendor in lock", w związku z czym nie ma potrzeby stosowania oprogramowania płatnego. Architektura zaprojektowano tak, żeby łatwo było ją rozbudować po fazie MVP.

Stack technologiczny oparty jest o Spring Reactor w tym:
  - Webflux 
  - Reactive MongoDB
  - Cloud Gateway

CI/CD:
  - Orkiestratorem kontenerów jest Docker Swarm, który zapewnia skalowalność, wydajność i bezpieczeństwo oraz rozwiązuje problemy SOA takie jak load-balancing czy service discovery
  - Wizualizacją Swarma zajmuje się Portainer
  - Deployment oparty jest o Jenkinsa z automatyczną weryfikacją brancha i wdrożonego serwisu w klastrze Dockera

SSO:
  - jako serwer autoryzacyjny wykorzystano Keycloak, o który oparty jest Api Gateway, dzięki czemu na wejściu mamy zapewnione pełne security OAuth2. Czujniki w celu wysłania danych muszę się zautentykować, jak również aplikacja webowa Angular wykorzystuje Keycloaka do logowania poprzez Browser Flow.

Portainer:
<p align="center">
  <img src="https://github.com/Crovitz/crbk-project/blob/main/portainer-dev.png?raw=true" />

Jenkins:
<p align="center">
  <img src="https://github.com/Crovitz/crbk-project/blob/main/jenkins-dev.png?raw=true" />

## Architektura aplikacyjna

Postawionym celem jest aby aplikacje były możliwie lekkie, trzymające reguły KISS (Keep It Simple, Stupid) oraz YAGNI (You Aren’t Gonna Need It).
Każdy z serwisów uwzględnia część pryncypiów z Domain Driven Design oraz Hexagonal Architecture.

Stosowane są value objecty, niemutowalność, package scope dla klas wskazujące na komponenty, które są publiczne dla innych modułów, odseparowana została domena aplikacji. W związku z tym posiadamy model persystentny, domenowy oraz widokowy (API).
W testach jednostkowych zastosowano bazę In Memory, która jest przyjazna dla TDD, dzięki czemu testy są szybkie (bez uruchamiania kontekstu).

## TODO:
Aplikacje wykorzystują programowanie reaktywne na razie w stopniu podstawowym. Warto zastanowić się nad wykorzystaniem Spring Cloud Stream, kiedy wystąpią serwisy pośredniczące w procesie. Ponadto kiedy Spring Cloud Native wyjdzie z fazy eksperymentalnej należy pomyśleć o jego zastosowaniu. Wtedy uzyskamy w pełni reaktywną, natywną architekturę aplikacyjną, co w środowisku dockerowym skutkować będzie ogromną oszczędnością zasobów.
Obecnie aplikacja zbiera dane tylko z czujników wilgotności gleby, w dalszych wydaniach będzie można sukcesywanie dodawać inne możliwe pomiary. Należy jeszcze:

- poprawić test SoilMoistureServiceIntegrationTest, aby nie było konieczności stawiania lokalnej instancji Rabbita (wykorzystać Qpid lub Rabbit Test)
- wykorzystać Spring Reactor w testach
- uszczelnić i zwiększyć pokrycie testów
- zrobić obsługę błędów HTTP dla Webfluxa
- dodać Mongobee jako system migracyjny dane dla MongoDB