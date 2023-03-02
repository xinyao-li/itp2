# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import intelligent_pb2 as intelligent__pb2


class IntelligentServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.plotPrediction = channel.unary_unary(
                '/intelligent.IntelligentService/plotPrediction',
                request_serializer=intelligent__pb2.PlotRequest.SerializeToString,
                response_deserializer=intelligent__pb2.PlotResponse.FromString,
                )
        self.pricePredict = channel.unary_unary(
                '/intelligent.IntelligentService/pricePredict',
                request_serializer=intelligent__pb2.PriceRequest.SerializeToString,
                response_deserializer=intelligent__pb2.PlotResponse.FromString,
                )


class IntelligentServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def plotPrediction(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def pricePredict(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_IntelligentServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'plotPrediction': grpc.unary_unary_rpc_method_handler(
                    servicer.plotPrediction,
                    request_deserializer=intelligent__pb2.PlotRequest.FromString,
                    response_serializer=intelligent__pb2.PlotResponse.SerializeToString,
            ),
            'pricePredict': grpc.unary_unary_rpc_method_handler(
                    servicer.pricePredict,
                    request_deserializer=intelligent__pb2.PriceRequest.FromString,
                    response_serializer=intelligent__pb2.PlotResponse.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'intelligent.IntelligentService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class IntelligentService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def plotPrediction(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/intelligent.IntelligentService/plotPrediction',
            intelligent__pb2.PlotRequest.SerializeToString,
            intelligent__pb2.PlotResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def pricePredict(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/intelligent.IntelligentService/pricePredict',
            intelligent__pb2.PriceRequest.SerializeToString,
            intelligent__pb2.PlotResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
