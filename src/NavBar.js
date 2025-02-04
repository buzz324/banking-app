import { Link } from 'react-router-dom';
import GraduateAcc from './accounts/GraduateAcc';
import SavingAcc from './accounts/SavingsAcc';
import Transaction from './accounts/Transaction';
import { BrowserRouter, Routes, Route } from 'react-router-dom';


const NavBar = () => {
    return  (
        <nav>
            <ul>
                <li>
                    <Link to="/savings">Savings Account</Link>
                </li>
                <li>
                    <Link to="/graduate">Graduate Account</Link>
                </li>
            </ul>
        </nav>
    );
}
export default NavBar;