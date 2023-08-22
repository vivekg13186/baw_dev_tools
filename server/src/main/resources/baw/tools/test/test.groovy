package baw.tools.test



input  = [ "inputName" :"" ]
res = baw.callService(  host,   username,   password,   "VS2",   "HelloService",   input)
assert res.status == 200
json = util.toJSON(res.getBody())
assert json["status"] == "200"
assert  json["data"]["data"]["outputMessage"]==""
print(json)

