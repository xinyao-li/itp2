from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class AutoBuyRequest(_message.Message):
    __slots__ = ["amount", "target", "ticker"]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    TARGET_FIELD_NUMBER: _ClassVar[int]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    amount: float
    target: float
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., target: _Optional[float] = ..., amount: _Optional[float] = ...) -> None: ...

class AutoBuyResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class AutoSellRequest(_message.Message):
    __slots__ = ["amount", "target", "ticker"]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    TARGET_FIELD_NUMBER: _ClassVar[int]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    amount: float
    target: float
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., target: _Optional[float] = ..., amount: _Optional[float] = ...) -> None: ...

class AutoSellResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class BalanceRequest(_message.Message):
    __slots__ = []
    def __init__(self) -> None: ...

class BalanceResponse(_message.Message):
    __slots__ = ["balance", "message"]
    BALANCE_FIELD_NUMBER: _ClassVar[int]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    balance: float
    message: str
    def __init__(self, balance: _Optional[float] = ..., message: _Optional[str] = ...) -> None: ...

class BuyRequest(_message.Message):
    __slots__ = ["amount", "ticker"]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    amount: float
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., amount: _Optional[float] = ...) -> None: ...

class BuyResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class CompanyRequest(_message.Message):
    __slots__ = ["ticker"]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    ticker: str
    def __init__(self, ticker: _Optional[str] = ...) -> None: ...

class CompanyResponse(_message.Message):
    __slots__ = ["company", "message"]
    COMPANY_FIELD_NUMBER: _ClassVar[int]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    company: str
    message: str
    def __init__(self, company: _Optional[str] = ..., message: _Optional[str] = ...) -> None: ...

class HoldingRequest(_message.Message):
    __slots__ = []
    def __init__(self) -> None: ...

class HoldingResponse(_message.Message):
    __slots__ = ["holds", "message"]
    HOLDS_FIELD_NUMBER: _ClassVar[int]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    holds: str
    message: str
    def __init__(self, holds: _Optional[str] = ..., message: _Optional[str] = ...) -> None: ...

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

class LogoutRequest(_message.Message):
    __slots__ = []
    def __init__(self) -> None: ...

class LogoutResponse(_message.Message):
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
    amount: float
    ticker: str
    def __init__(self, ticker: _Optional[str] = ..., amount: _Optional[float] = ...) -> None: ...

class SellResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class StopBuyRequest(_message.Message):
    __slots__ = ["ticker"]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    ticker: str
    def __init__(self, ticker: _Optional[str] = ...) -> None: ...

class StopBuyResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class StopSellRequest(_message.Message):
    __slots__ = ["ticker"]
    TICKER_FIELD_NUMBER: _ClassVar[int]
    ticker: str
    def __init__(self, ticker: _Optional[str] = ...) -> None: ...

class StopSellResponse(_message.Message):
    __slots__ = ["message", "success"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    message: str
    success: bool
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...
