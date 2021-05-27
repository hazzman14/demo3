import React, { Component } from 'react';
import './App.css';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ActorList from "./ActorList";
import Home from './Home';


class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/actors' exact={true} component={ActorList}/>
                </Switch>
            </Router>
        )
    }
}

export default App;