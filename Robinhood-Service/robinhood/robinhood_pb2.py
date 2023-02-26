# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: robinhood.proto
"""Generated protocol buffer code."""
from google.protobuf.internal import builder as _builder
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0frobinhood.proto\x12\trobinhood\"2\n\x0cLoginRequest\x12\x10\n\x08username\x18\x01 \x01(\t\x12\x10\n\x08password\x18\x02 \x01(\t\"1\n\rLoginResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"\x1e\n\x0cQuoteRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\"/\n\rQuoteResponse\x12\r\n\x05price\x18\x01 \x01(\x01\x12\x0f\n\x07message\x18\x02 \x01(\t\",\n\nBuyRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x0e\n\x06\x61mount\x18\x02 \x01(\x05\"/\n\x0b\x42uyResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"-\n\x0bSellRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x0e\n\x06\x61mount\x18\x02 \x01(\x05\"0\n\x0cSellResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\" \n\x0e\x41piTestRequest\x12\x0e\n\x06\x61mount\x18\x01 \x01(\x01\"3\n\x0f\x41piTestResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t2\xc5\x02\n\x10RobinhoodService\x12<\n\x05LOGIN\x12\x17.robinhood.LoginRequest\x1a\x18.robinhood.LoginResponse\"\x00\x12<\n\x05QUOTE\x12\x17.robinhood.QuoteRequest\x1a\x18.robinhood.QuoteResponse\"\x00\x12\x36\n\x03\x42UY\x12\x15.robinhood.BuyRequest\x1a\x16.robinhood.BuyResponse\"\x00\x12\x39\n\x04SELL\x12\x16.robinhood.SellRequest\x1a\x17.robinhood.SellResponse\"\x00\x12\x42\n\x07\x41piTest\x12\x19.robinhood.ApiTestRequest\x1a\x1a.robinhood.ApiTestResponse\"\x00\x42\x1c\n\x18\x63om.aaron.grpc.robinhoodP\x01\x62\x06proto3')

_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, globals())
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'robinhood_pb2', globals())
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  DESCRIPTOR._serialized_options = b'\n\030com.aaron.grpc.robinhoodP\001'
  _LOGINREQUEST._serialized_start=30
  _LOGINREQUEST._serialized_end=80
  _LOGINRESPONSE._serialized_start=82
  _LOGINRESPONSE._serialized_end=131
  _QUOTEREQUEST._serialized_start=133
  _QUOTEREQUEST._serialized_end=163
  _QUOTERESPONSE._serialized_start=165
  _QUOTERESPONSE._serialized_end=212
  _BUYREQUEST._serialized_start=214
  _BUYREQUEST._serialized_end=258
  _BUYRESPONSE._serialized_start=260
  _BUYRESPONSE._serialized_end=307
  _SELLREQUEST._serialized_start=309
  _SELLREQUEST._serialized_end=354
  _SELLRESPONSE._serialized_start=356
  _SELLRESPONSE._serialized_end=404
  _APITESTREQUEST._serialized_start=406
  _APITESTREQUEST._serialized_end=438
  _APITESTRESPONSE._serialized_start=440
  _APITESTRESPONSE._serialized_end=491
  _ROBINHOODSERVICE._serialized_start=494
  _ROBINHOODSERVICE._serialized_end=819
# @@protoc_insertion_point(module_scope)
