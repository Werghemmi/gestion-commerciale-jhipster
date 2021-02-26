import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DevisDetailComponent } from 'app/entities/devis/devis-detail.component';
import { Devis } from 'app/shared/model/devis.model';

describe('Component Tests', () => {
  describe('Devis Management Detail Component', () => {
    let comp: DevisDetailComponent;
    let fixture: ComponentFixture<DevisDetailComponent>;
    const route = ({ data: of({ devis: new Devis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DevisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DevisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DevisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load devis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.devis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
