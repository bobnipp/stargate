import {Component} from '@angular/core'
import {FilterService} from '../../services/filter.service'
import {ClearableFilterComponent} from '../filter/clearable-filter.component'
import {SelectedFilterOptionState} from "../../models/record-filter-state.model";

@Component({
    selector: 'stargate-filter-search',
    templateUrl: './filter-search.component.html',
    styleUrls: ['./filter-search.component.scss', '../../sidebar/form.scss']
})
export class FilterSearchComponent implements ClearableFilterComponent {

    constructor(private filterService: FilterService) {
        this.filterService.storedFilters.subscribe((result: SelectedFilterOptionState) => {
            this.searchInput = result.keywordSearch
        })
    }

    searchInput: string = ''

    search() {
        this.filterService.updateSearchFilter(this.searchInput)
    }

    searchOnEnter(e) {
        if (e.key === 'Enter') this.search()
    }

    clearCallback() {
        this.searchInput = ''
        this.filterService.updateSearchFilterDontApply(this.searchInput)
    }
}
