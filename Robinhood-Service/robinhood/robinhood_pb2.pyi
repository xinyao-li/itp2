from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class ApiTestRequest(_message.Message):
    __slots__ = ["amount"]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    amount: float
    def __init__(self, amount: _Optional[float] = ...) -> None: ...

class ApiTestResponse(_message.Message):
    __slots__ = ["amount", "message"]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    amount: float
    message: str
    def __init__(self, amount: _Optional[float] = ..., message: _Optional[str] = ...) -> None: ...

class BuyRequest(_message.Message):
    __slots__ = ["amount", "ticker"]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    amount: int
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., amount: _Optional[int] = ...) -> None: ...

class BuyResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class LoginRequest(_message.Message):
    __slots__ = ["password", "username"]
    PASSWORD_FIELD_NUMBER: _ClassVar[int]
    USERNAME_FIELD_NUMBER: _ClassVar[int]
    password: str
    username: str
    def __init__(self, username: _Optional[str] = ..., password: _Optional[str] = ...) -> None: ...

class LoginResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class QuoteRequest(_message.Message):
    __slots__ = ["ticker"]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    ticker: str
    def __init__(self, ticker: _Optional[str] = ...) -> None: ...

class QuoteResponse(_message.Message):
    __slots__ = ["message", "price"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    PRICE_FIELD_NUMBER: _ClassVar[int]
    message: str
    price: float
    def __init__(self, price: _Optional[float] = ..., message: _Optional[str] = ...) -> None: ...

class SellRequest(_message.Message):
    __slots__ = ["amount", "ticker"]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    amount: int
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., amount: _Optional[int] = ...) -> None: ...

class SellResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...
