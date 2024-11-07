import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Sellers from  '@/pages/Sellers.vue';
import SellerDetail from  '@/pages/SellerDetail.vue';
import Buyers from  '@/pages/Buyers.vue';
import BuyerDetail from  '@/pages/BuyerDetail.vue';
import Products from  '@/pages/Products.vue';
import ProductDetail from  '@/pages/ProductDetail.vue';
import Auctions from  '@/pages/Auctions.vue';
import AuctionDetail from  '@/pages/AuctionDetail.vue';
import Bids from  '@/pages/Bids.vue';
import BidDetail from  '@/pages/BidDetail.vue';
import Orders from  '@/pages/Orders.vue';
import OrderDetail from  '@/pages/OrderDetail.vue';
import OrderItems from  '@/pages/OrderItems.vue';
import OrderItemDetail from  '@/pages/OrderItemDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Categorys from  '@/pages/Categorys.vue';
import CategoryDetail from  '@/pages/CategoryDetail.vue';
import ProductCategorys from  '@/pages/ProductCategorys.vue';
import ProductCategoryDetail from  '@/pages/ProductCategoryDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Shippings from  '@/pages/Shippings.vue';
import ShippingDetail from  '@/pages/ShippingDetail.vue';
import Addresss from  '@/pages/Addresss.vue';
import AddressDetail from  '@/pages/AddressDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Wishlists from  '@/pages/Wishlists.vue';
import WishlistDetail from  '@/pages/WishlistDetail.vue';
import Messages from  '@/pages/Messages.vue';
import MessageDetail from  '@/pages/MessageDetail.vue';
import Disputes from  '@/pages/Disputes.vue';
import DisputeDetail from  '@/pages/DisputeDetail.vue';
import ReturnRequests from  '@/pages/ReturnRequests.vue';
import ReturnRequestDetail from  '@/pages/ReturnRequestDetail.vue';
import Coupons from  '@/pages/Coupons.vue';
import CouponDetail from  '@/pages/CouponDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/users',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/sellers',
		name: 'Sellers',
		layout: DefaultLayout,
		component: Sellers,
	},
	{
	    path: '/seller/:sellerId', 
	    name: 'SellerDetail',
		layout: DefaultLayout,
	    component: SellerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/buyers',
		name: 'Buyers',
		layout: DefaultLayout,
		component: Buyers,
	},
	{
	    path: '/buyer/:buyerId', 
	    name: 'BuyerDetail',
		layout: DefaultLayout,
	    component: BuyerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/products',
		name: 'Products',
		layout: DefaultLayout,
		component: Products,
	},
	{
	    path: '/product/:productId', 
	    name: 'ProductDetail',
		layout: DefaultLayout,
	    component: ProductDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/auctions',
		name: 'Auctions',
		layout: DefaultLayout,
		component: Auctions,
	},
	{
	    path: '/auction/:auctionId', 
	    name: 'AuctionDetail',
		layout: DefaultLayout,
	    component: AuctionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bids',
		name: 'Bids',
		layout: DefaultLayout,
		component: Bids,
	},
	{
	    path: '/bid/:bidId', 
	    name: 'BidDetail',
		layout: DefaultLayout,
	    component: BidDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/orders',
		name: 'Orders',
		layout: DefaultLayout,
		component: Orders,
	},
	{
	    path: '/order/:orderId', 
	    name: 'OrderDetail',
		layout: DefaultLayout,
	    component: OrderDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/orderItems',
		name: 'OrderItems',
		layout: DefaultLayout,
		component: OrderItems,
	},
	{
	    path: '/orderItem/:orderItemId', 
	    name: 'OrderItemDetail',
		layout: DefaultLayout,
	    component: OrderItemDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/categorys',
		name: 'Categorys',
		layout: DefaultLayout,
		component: Categorys,
	},
	{
	    path: '/category/:categoryId', 
	    name: 'CategoryDetail',
		layout: DefaultLayout,
	    component: CategoryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/productCategorys',
		name: 'ProductCategorys',
		layout: DefaultLayout,
		component: ProductCategorys,
	},
	{
	    path: '/productCategory/:productCategoryId', 
	    name: 'ProductCategoryDetail',
		layout: DefaultLayout,
	    component: ProductCategoryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/shippings',
		name: 'Shippings',
		layout: DefaultLayout,
		component: Shippings,
	},
	{
	    path: '/shipping/:shippingId', 
	    name: 'ShippingDetail',
		layout: DefaultLayout,
	    component: ShippingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/addresss',
		name: 'Addresss',
		layout: DefaultLayout,
		component: Addresss,
	},
	{
	    path: '/address/:addressId', 
	    name: 'AddressDetail',
		layout: DefaultLayout,
	    component: AddressDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/wishlists',
		name: 'Wishlists',
		layout: DefaultLayout,
		component: Wishlists,
	},
	{
	    path: '/wishlist/:wishlistId', 
	    name: 'WishlistDetail',
		layout: DefaultLayout,
	    component: WishlistDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/messages',
		name: 'Messages',
		layout: DefaultLayout,
		component: Messages,
	},
	{
	    path: '/message/:messageId', 
	    name: 'MessageDetail',
		layout: DefaultLayout,
	    component: MessageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/disputes',
		name: 'Disputes',
		layout: DefaultLayout,
		component: Disputes,
	},
	{
	    path: '/dispute/:disputeId', 
	    name: 'DisputeDetail',
		layout: DefaultLayout,
	    component: DisputeDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/returnRequests',
		name: 'ReturnRequests',
		layout: DefaultLayout,
		component: ReturnRequests,
	},
	{
	    path: '/returnRequest/:returnRequestId', 
	    name: 'ReturnRequestDetail',
		layout: DefaultLayout,
	    component: ReturnRequestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coupons',
		name: 'Coupons',
		layout: DefaultLayout,
		component: Coupons,
	},
	{
	    path: '/coupon/:couponId', 
	    name: 'CouponDetail',
		layout: DefaultLayout,
	    component: CouponDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
