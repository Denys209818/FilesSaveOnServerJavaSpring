import React from "react";


interface RouteInfo 
{
    element: React.FC,
    path: string
}

const Main = React.lazy(() => import("../components/Default/Main/index"));
const Author = React.lazy(() => import("../components/Default/Author/index"));
const Book = React.lazy(() => import("../components/Default/Book/index"));


export const DefaultRoutes: Array<RouteInfo> = 
[
    {path: '/author', element: Author},
    {path: '/book', element: Book},
    {path: "/", element:Main}
];