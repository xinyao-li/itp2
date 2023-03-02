from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class PlotRequest(_message.Message):
    __slots__ = ["endDate", "startDate", "ticker"]
    ENDDATE_FIELD_NUMBER: _ClassVar[int]
    STARTDATE_FIELD_NUMBER: _ClassVar[int]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    endDate: str
    startDate: str
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., startDate: _Optional[str] = ..., endDate: _Optional[str] = ...) -> None: ...

class PlotResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class PriceRequest(_message.Message):
    __slots__ = ["endDate", "startDate", "ticker"]
    ENDDATE_FIELD_NUMBER: _ClassVar[int]
    STARTDATE_FIELD_NUMBER: _ClassVar[int]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    endDate: str
    startDate: str
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., startDate: _Optional[str] = ..., endDate: _Optional[str] = ...) -> None: ...

class PriceResponse(_message.Message):
    __slots__ = ["message", "price"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    PRICE_FIELD_NUMBER: _ClassVar[int]
    message: str
    price: float
    def __init__(self, price: _Optional[float] = ..., message: _Optional[str] = ...) -> None: ...
