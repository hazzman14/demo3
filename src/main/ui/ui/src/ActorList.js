import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';

class ActorList extends Component {

    constructor(props) {
        super(props);
        this.state = {actors: []};

    }

    componentDidMount() {
        fetch('/api/actors/all')
            .then(response => response.json())
            .then(data => this.setState({actors: data}));
    }

    render() {
        const {actors, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const actorList = actors.map(actor => {
            return <tr key={actor.id}>
                <td style={{whiteSpace: 'nowrap'}}>{actor.id}</td>
                <td>{actor.firstName}</td>
                <td>{actor.lastName}</td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/api/actors/create">Add Actor</Button>
                    </div>
                    <p></p>
                    <h3>List of Actors</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>ID:</th>
                            <th>First Name:</th>
                            <th>Last Name:</th>
                        </tr>
                        </thead>
                        <tbody>
                        {actorList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }

}
export default ActorList;