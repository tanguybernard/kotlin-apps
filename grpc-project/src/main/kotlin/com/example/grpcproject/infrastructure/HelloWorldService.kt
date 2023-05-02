package com.example.grpcproject.infrastructure

class HelloWorldService : GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun sayHello(request: HelloWorld.HelloRequest) = HelloWorld.HelloReply
        .newBuilder()
        .setMessage("Hello ${request.name}")
        .build()

    override suspend fun sayHelloAgain(request: HelloWorld.HelloRequest) = HelloWorld.HelloReply
        .newBuilder()
        .setMessage("Hello again ${request.name}")
        .build()
}