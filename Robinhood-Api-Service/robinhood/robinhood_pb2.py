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




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0frobinhood.proto\x12\trobinhood\"2\n\x0cLoginRequest\x12\x10\n\x08username\x18\x01 \x01(\t\x12\x10\n\x08password\x18\x02 \x01(\t\"1\n\rLoginResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"\x0f\n\rLogoutRequest\"2\n\x0eLogoutResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"\x1e\n\x0cQuoteRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\"/\n\rQuoteResponse\x12\r\n\x05price\x18\x01 \x01(\x01\x12\x0f\n\x07message\x18\x02 \x01(\t\",\n\nBuyRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x0e\n\x06\x61mount\x18\x02 \x01(\x01\"/\n\x0b\x42uyResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"-\n\x0bSellRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x0e\n\x06\x61mount\x18\x02 \x01(\x01\"0\n\x0cSellResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"\x10\n\x0e\x42\x61lanceRequest\"3\n\x0f\x42\x61lanceResponse\x12\x0f\n\x07\x62\x61lance\x18\x01 \x01(\x01\x12\x0f\n\x07message\x18\x02 \x01(\t\"@\n\x0e\x41utoBuyRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x0e\n\x06target\x18\x02 \x01(\x01\x12\x0e\n\x06\x61mount\x18\x03 \x01(\x01\"3\n\x0f\x41utoBuyResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"A\n\x0f\x41utoSellRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\x12\x0e\n\x06target\x18\x02 \x01(\x01\x12\x0e\n\x06\x61mount\x18\x03 \x01(\x01\"4\n\x10\x41utoSellResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\" \n\x0e\x43ompanyRequest\x12\x0e\n\x06ticker\x18\x01 \x01(\t\"3\n\x0f\x43ompanyResponse\x12\x0f\n\x07\x63ompany\x18\x01 \x01(\t\x12\x0f\n\x07message\x18\x02 \x01(\t2\xdb\x04\n\x10RobinhoodService\x12<\n\x05login\x12\x17.robinhood.LoginRequest\x1a\x18.robinhood.LoginResponse\"\x00\x12?\n\x06logout\x12\x18.robinhood.LogoutRequest\x1a\x19.robinhood.LogoutResponse\"\x00\x12<\n\x05quote\x12\x17.robinhood.QuoteRequest\x1a\x18.robinhood.QuoteResponse\"\x00\x12\x36\n\x03\x62uy\x12\x15.robinhood.BuyRequest\x1a\x16.robinhood.BuyResponse\"\x00\x12\x39\n\x04sell\x12\x16.robinhood.SellRequest\x1a\x17.robinhood.SellResponse\"\x00\x12\x45\n\ngetBalance\x12\x19.robinhood.BalanceRequest\x1a\x1a.robinhood.BalanceResponse\"\x00\x12\x42\n\x07\x61utoBuy\x12\x19.robinhood.AutoBuyRequest\x1a\x1a.robinhood.AutoBuyResponse\"\x00\x12\x45\n\x08\x61utoSell\x12\x1a.robinhood.AutoSellRequest\x1a\x1b.robinhood.AutoSellResponse\"\x00\x12\x45\n\ngetCompany\x12\x19.robinhood.CompanyRequest\x1a\x1a.robinhood.CompanyResponse\"\x00\x42\x1c\n\x18\x63om.aaron.grpc.robinhoodP\x01\x62\x06proto3')

_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, globals())
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'robinhood_pb2', globals())
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  DESCRIPTOR._serialized_options = b'\n\030com.aaron.grpc.robinhoodP\001'
  _LOGINREQUEST._serialized_start=30
  _LOGINREQUEST._serialized_end=80
  _LOGINRESPONSE._serialized_start=82
  _LOGINRESPONSE._serialized_end=131
  _LOGOUTREQUEST._serialized_start=133
  _LOGOUTREQUEST._serialized_end=148
  _LOGOUTRESPONSE._serialized_start=150
  _LOGOUTRESPONSE._serialized_end=200
  _QUOTEREQUEST._serialized_start=202
  _QUOTEREQUEST._serialized_end=232
  _QUOTERESPONSE._serialized_start=234
  _QUOTERESPONSE._serialized_end=281
  _BUYREQUEST._serialized_start=283
  _BUYREQUEST._serialized_end=327
  _BUYRESPONSE._serialized_start=329
  _BUYRESPONSE._serialized_end=376
  _SELLREQUEST._serialized_start=378
  _SELLREQUEST._serialized_end=423
  _SELLRESPONSE._serialized_start=425
  _SELLRESPONSE._serialized_end=473
  _BALANCEREQUEST._serialized_start=475
  _BALANCEREQUEST._serialized_end=491
  _BALANCERESPONSE._serialized_start=493
  _BALANCERESPONSE._serialized_end=544
  _AUTOBUYREQUEST._serialized_start=546
  _AUTOBUYREQUEST._serialized_end=610
  _AUTOBUYRESPONSE._serialized_start=612
  _AUTOBUYRESPONSE._serialized_end=663
  _AUTOSELLREQUEST._serialized_start=665
  _AUTOSELLREQUEST._serialized_end=730
  _AUTOSELLRESPONSE._serialized_start=732
  _AUTOSELLRESPONSE._serialized_end=784
  _COMPANYREQUEST._serialized_start=786
  _COMPANYREQUEST._serialized_end=818
  _COMPANYRESPONSE._serialized_start=820
  _COMPANYRESPONSE._serialized_end=871
  _ROBINHOODSERVICE._serialized_start=874
  _ROBINHOODSERVICE._serialized_end=1477
# @@protoc_insertion_point(module_scope)