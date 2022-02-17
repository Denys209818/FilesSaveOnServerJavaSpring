import React, { Suspense } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import DefaultLayout from './layouts/Default/DefaultLayout';
import { DefaultRoutes } from './routes/DefaultRoutes';

const App = () => {
  return (
   <>
   <Suspense fallback={<>Загрузка...</>}>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<DefaultLayout/>}>
          {DefaultRoutes.map((element, index) => {
            return <Route key={index} path={element.path} element={<element.element/>}/>
          })}
        </Route>
      </Routes>
    </BrowserRouter>
    </Suspense>
   </>
  );
}

export default App;
