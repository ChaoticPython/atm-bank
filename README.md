# ATM-BANK.

Esta es solo una simulación ficticia de las operaciones básicas que puede realizar un cajero automático.

Se van a realizar las siguientes operaciones:

* Consultar saldo: podremos consultar el saldo de nuestra cuenta (por medio de código duro o un valor aleatorio).
* Retirar dinero: podremos retirar dinero (tomando en cuenta sus excepciones).
* Realizar depósito: podremos realizar depositos unicamente a nuestra cuenta ficticia. 
* Transferir a una cuenta: podremos realizar transferencias a otras cuentas ficticias (tomando en cuenta sus excepciones).

El fin del proyecto es familiarizarse con github.

============================================================================

# NOTAS.

En esta sección iremos tomando notas de los comandos que hemos estado utilizando.

* Creamos nuestro proyecto y a su vez el repositorio.
* Inicializamos git en nuestro proyecto local con **git init**.
* Añadimos nuestros cambios al stage por medio de **git add --all**
* Hacemos **commit** de los cambios realizados (creación del proyecto) con **git commit -m "mensaje"**
* Creamos (o hacemos referencia a) nuestro repositorio remoto siguiendo los pasos:
	* Primero colocamos el siguiente comando **git remote add origin 'URL'** donde **URL** es 
	  la dirección de nuestro repositorio en github. De esta forma ya tenemos una referencia 
	  a nuestro repositorio en github.
	* Posteriormente realizamos un push al repositorio que queremos (en nuestro caso el único 
	  que tenemos) por medio de **git push -u origin master**.
* La branch master **NO** la vamos a tocar más allá de lo que realizamos.
* Crearemos una nueva rama que se llamara **development** y nos moveremos a ésta.
* A continuación creamos un archivo README.md donde describiremos el proposito del proyecto.
* Hacemos **commit** de los cambios y luego un **push** pero a la nueva rama que creamos (**development**).
* Ahora creamos una nueva rama llamada **feature/initial-dev** en donde vamos a trabajar con todos los 
  cambios que realizemos en nuestra aplicación, con el fin de no afectar el desarrollo en development.
