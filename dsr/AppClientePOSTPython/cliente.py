import http.client

username = "sulem"
password = "melus38"

ip = "192.168.56.103"
puerto = "8080"

headers = {"Content-type" : "application/x-www-form-urlencoded"}

data = "username=" + username + "&password" + password

conn = http.client.HTTPConnection(ip,puerto)

conn.request("POST", "/login", body=data, headers=headers)

response = conn.getresponse()

if response.status == 200:
    print("Inicio de sesión exitosamente")
else:
    print("Error en el inicio de sesión", response.reason)

conn.close()