# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: intelligent.proto
"""Generated protocol buffer code."""
from google.protobuf.internal import builder as _builder
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x11intelligent.proto\x12\x0bintelligent\"A\n\x0bPlotRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x11\n\tstartDate\x18\x02 \x01(\t\x12\x0f\n\x07\x65ndDate\x18\x03 \x01(\t\"0\n\x0cPlotResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"B\n\x0cPriceRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x11\n\tstartDate\x18\x02 \x01(\t\x12\x0f\n\x07\x65ndDate\x18\x03 \x01(\t\"/\n\rPriceResponse\x12\r\n\x05price\x18\x01 \x01(\x01\x12\x0f\n\x07message\x18\x02 \x01(\t2\xa5\x01\n\x12IntelligentService\x12G\n\x0eplotPrediction\x12\x18.intelligent.PlotRequest\x1a\x19.intelligent.PlotResponse\"\x00\x12\x46\n\x0cpricePredict\x12\x19.intelligent.PriceRequest\x1a\x19.intelligent.PlotResponse\"\x00\x42\x1e\n\x1a\x63om.aaron.grpc.intelligentP\x01\x62\x06proto3')

_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, globals())
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'intelligent_pb2', globals())
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  DESCRIPTOR._serialized_options = b'\n\032com.aaron.grpc.intelligentP\001'
  _PLOTREQUEST._serialized_start=34
  _PLOTREQUEST._serialized_end=99
  _PLOTRESPONSE._serialized_start=101
  _PLOTRESPONSE._serialized_end=149
  _PRICEREQUEST._serialized_start=151
  _PRICEREQUEST._serialized_end=217
  _PRICERESPONSE._serialized_start=219
  _PRICERESPONSE._serialized_end=266
  _INTELLIGENTSERVICE._serialized_start=269
  _INTELLIGENTSERVICE._serialized_end=434
# @@protoc_insertion_point(module_scope)
