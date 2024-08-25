# Informe sobre el Uso de `ExecutorService` y el Patrón Observer en un Sistema de Pedidos

## Introducción

Este informe da un vistazo rápido a cómo el uso de `ExecutorService` y el patrón de diseño Observer impacta en un sistema de pedidos en Java. Vamos a ver los pros, los contras y qué podría pasar si no estuvieran implementados.

## 1. `ExecutorService` con `Callable`

### Beneficios

- **Procesamiento en paralelo:** Usar `ExecutorService` te permite manejar varios pedidos al mismo tiempo, lo que mejora el rendimiento del sistema, especialmente cuando hay muchos pedidos que procesar.

- **Gestión eficiente de hilos:** `ExecutorService` se encarga de manejar los hilos por ti, así que no tienes que preocuparte por crearlos y destruirlos constantemente.

- **Manejo de tareas en segundo plano:** Con `Callable` y `Future`, puedes ejecutar tareas en segundo plano y recuperar los resultados cuando estén listos, lo que hace que tu código sea más limpio y manejable.

### Desventajas

- **Aumenta la complejidad:** Usar `ExecutorService` y `Callable` añade algo de complejidad al código. Tienes que estar más atento a posibles errores, como condiciones de carrera o bloqueos.

- **Costos de gestión de hilos:** Aunque `ExecutorService` es eficiente, todavía hay un costo asociado con mantener un grupo de hilos, lo que podría no ser ideal si estás trabajando con recursos limitados.

### ¿Qué pasaría sin `ExecutorService`?

- **Procesamiento más lento:** Si no usas `ExecutorService`, los pedidos se procesarán uno por uno, lo que puede hacer que el sistema sea más lento, sobre todo si tienes muchas solicitudes.

- **Posibles bloqueos:** Sin la concurrencia que ofrece `ExecutorService`, el hilo principal podría bloquearse mientras espera que los pedidos se completen, lo que afectaría la capacidad de respuesta de la aplicación.

## 2. Patrón Observer

### Beneficios

- **Desacoplamiento:** Con el patrón Observer, `Pedido` (el observable) y `Cliente` (los observadores) están menos acoplados. Esto significa que los pedidos no necesitan saber detalles específicos de los clientes, solo que deben notificarles cuando algo cambia.

- **Facilidad para expandir:** Si quieres agregar más observadores (clientes), puedes hacerlo sin tocar el código del pedido, lo que facilita la expansión del sistema.

- **Notificaciones automáticas:** Los clientes reciben automáticamente una notificación cuando cambia el estado de su pedido, lo que mejora la comunicación en el sistema.

### Desventajas

- **Mayor complejidad:** Implementar el patrón Observer puede hacer que tu código sea un poco más complicado. Necesitas asegurarte de que las notificaciones funcionen bien y que todos los observadores estén correctamente registrados.

- **Riesgos de rendimiento:** Si tienes demasiados observadores, el proceso de notificación puede volverse pesado y afectar el rendimiento del sistema.

### ¿Qué pasaría sin el patrón Observer?

- **Código más enredado:** Sin el patrón Observer, `Pedido` tendría que saber y manejar directamente a cada cliente, lo que haría el código más complejo y difícil de mantener.

- **Notificaciones más difíciles:** Sin una estructura como el patrón Observer, sería más complicado asegurarte de que todos los clientes reciban las actualizaciones correctas sobre el estado de sus pedidos.

## Conclusión

El uso de `ExecutorService` y el patrón Observer trae bastantes beneficios, como un mejor rendimiento y un código más flexible. Sin embargo, también hacen que el código sea más complejo. Si no los usas, tu sistema podría ser más simple, pero a costa de ser menos eficiente y más difícil de escalar.
