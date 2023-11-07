# install pip matplot
#
from typing import List, Any
import matplotlib.pyplot as plt

def product_price(p, percentange_increase=0.05):
    return p + p * percentange_increase


def total_sales(s, percentange_increase=0.03):
    return s - s * percentange_increase


def total_revenue(p, s):
    return p * s


def price_over_time(init_sales, t_max=10) -> List[Any]:
    s = init_sales
    for t in range(0, t_max + 1):
        s = product_price(s)
        yield s


def total_sales_over_time(init_price, t_max=10) -> List[Any]:
    p = init_price
    for t in range(0, t_max + 1):
        p = total_sales(p)
        yield p


def total_revenue_over_time(init_price, init_sales, t_max=10) -> List[Any]:
    p = init_price
    s = init_sales
    for t in range(0, t_max + 1):
        p = product_price(p)
        s = total_sales(s)
        yield total_revenue(p, s)


if __name__ == "__main__":
    t_max = 300
    plt.plot(list(total_revenue_over_time(100, 100, t_max=t_max)), color="red")
    plt.ylabel("Rev over time")

    plt.plot(list(price_over_time(100, t_max=t_max)), color="blue")
    plt.ylabel("Total price")

    plt.plot(list(total_sales_over_time(100, tx_max=t_max)), color="yellow")
    plt.ylabel("Total sales")
    plt.show()
